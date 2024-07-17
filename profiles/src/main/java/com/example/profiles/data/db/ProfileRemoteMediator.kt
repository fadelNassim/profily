package com.example.profiles.data.db

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.profiles.data.api.ProfilesApi
import com.example.profiles.data.db.entities.ProfileEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ProfileRemoteMediator(
    private val profilesDb: ProfilesDataBase,
    private val profilesApi: ProfilesApi
) : RemoteMediator<Int, ProfileEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProfileEntity>
    ): MediatorResult {
        return try {
            println("MEDIATOR LOAD TYPE " + loadType.name)
            // The network load method takes an optional after=<user.id>
            // parameter. For every page after the first, pass the last user
            // ID to let it continue from where it left off. For REFRESH,
            // pass null to load the first page.
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else
                        (lastItem.id / state.config.pageSize) + 1
                }
            }

            // Suspending network load via Retrofit. This doesn't need to be
            // wrapped in a withContext(Dispatcher.IO) { ... } block since
            // Retrofit's Coroutine CallAdapter dispatches on a worker
            // thread.
            val profilesResponse = profilesApi.getProfiles(
                pageNumber = loadKey,
                profileCount = state.config.pageSize
            )

            profilesDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    profilesDb.profilesDao().clearAll()
                }

                // Insert new users into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                profilesDb.profilesDao().insertProfile(profilesResponse.profiles.map {
                    ProfileEntity(
                        name = it.name.first + " " + it.name.last,
                        email = it.email,
                        dob = it.dob.date,
                        phone = it.phone,
                        picture = it.picture.large
                    )
                })
            }

            MediatorResult.Success(
                endOfPaginationReached = profilesResponse.profiles.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
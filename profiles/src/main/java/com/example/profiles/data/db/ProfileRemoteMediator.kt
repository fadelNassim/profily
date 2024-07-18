package com.example.profiles.data.db

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.profiles.data.api.ProfilesApi
import com.example.profiles.data.db.entities.ProfileEntity

@OptIn(ExperimentalPagingApi::class)
class ProfileRemoteMediator(
    private val profilesDb: ProfilesDataBase,
    private val profilesApi: ProfilesApi,
) : RemoteMediator<Int, ProfileEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProfileEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
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
            val profilesResponse = profilesApi.getProfiles(
                pageNumber = loadKey,
                profileCount = state.config.pageSize
            )

            profilesDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    profilesDb.profilesDao().clearAll()
                }
                profilesDb.profilesDao().insertProfile(profilesResponse.profiles.map { profile ->
                    ProfileEntity(
                        name = "${profile.name.first} ${profile.name.last}",
                        email = profile.email,
                        birthDate = profile.birthDate.date,
                        phone = profile.phone,
                        picture = profile.picture.large,
                        country = profile.location.country,
                        gender = profile.gender
                    )
                })
            }

            MediatorResult.Success(
                endOfPaginationReached = profilesResponse.profiles.isEmpty()
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
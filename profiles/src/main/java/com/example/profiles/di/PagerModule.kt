package com.example.profiles.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.profiles.data.api.ProfilesApi
import com.example.profiles.data.db.ProfileRemoteMediator
import com.example.profiles.data.db.ProfilesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PagerModule {
    @Provides
    @Singleton
    fun provideProfileRemoteMediator(
        dataBase: ProfilesDataBase,
        api: ProfilesApi
    ) = ProfileRemoteMediator(profilesDb = dataBase, profilesApi = api)


    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideProfilessPager(dataBase: ProfilesDataBase, remoteMediator: ProfileRemoteMediator) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10,
            ),
            remoteMediator = remoteMediator,
            pagingSourceFactory = { dataBase.profilesDao().pagingSource() }
        )
}
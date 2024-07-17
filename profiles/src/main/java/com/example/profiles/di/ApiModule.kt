package com.example.profiles.di

import com.example.profiles.data.api.ProfilesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun provideProfilesApi(retrofit: Retrofit): ProfilesApi = retrofit.create(ProfilesApi::class.java)
}
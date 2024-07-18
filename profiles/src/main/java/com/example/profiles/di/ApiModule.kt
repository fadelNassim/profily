package com.example.profiles.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.profiles.data.api.ProfilesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun provideProfilesApi(retrofit: Retrofit): ProfilesApi = retrofit.create(ProfilesApi::class.java)

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context) = context.getSystemService(
        Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}
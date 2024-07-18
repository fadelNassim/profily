package com.example.profiles.di

import android.content.Context
import androidx.room.Room
import com.example.profiles.data.db.dao.ProfilesDao
import com.example.profiles.data.db.ProfilesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ProfilesDataBase {
        return Room.databaseBuilder(
            context,
            ProfilesDataBase::class.java,
            "profiles.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideProfilesDao(database: ProfilesDataBase): ProfilesDao {
        return database.profilesDao()
    }
}
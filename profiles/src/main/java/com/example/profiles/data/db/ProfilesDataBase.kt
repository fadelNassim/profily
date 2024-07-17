package com.example.profiles.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.profiles.data.db.dao.ProfilesDao
import com.example.profiles.data.db.entities.ProfileEntity

@Database(entities = [ProfileEntity::class], version = 1)
abstract class ProfilesDataBase : RoomDatabase() {
    abstract fun profilesDao(): ProfilesDao
}
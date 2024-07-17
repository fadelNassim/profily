package com.example.profiles.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.profiles.data.db.entities.ProfileEntity

@Dao
interface ProfilesDao {
    @Upsert
    fun insertProfile(profiles: List<ProfileEntity>)

    @Query("SELECT * FROM profiles")
    fun pagingSource(): PagingSource<Int, ProfileEntity>

    @Query("DELETE FROM profiles")
    suspend fun clearAll()
}
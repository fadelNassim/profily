package com.example.profiles.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.example.profiles.data.db.entities.ProfileEntity
import com.example.profiles.domain.models.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfilesRepository @Inject constructor(private val pager: Pager<Int, ProfileEntity>) {
    fun fetchProfiles(): Flow<PagingData<Profile>> = pager.flow.map { pagingData ->
        pagingData.map {
            Profile(
                name = it.name,
                email = it.email,
                phone = it.phone,
                picture = it.picture,
                dob = it.dob,
                id = it.id
            )
        }
    }
}
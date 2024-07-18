package com.example.profiles.data.repositories

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.example.profiles.data.db.dao.ProfilesDao
import com.example.profiles.data.db.entities.ProfileEntity
import com.example.profiles.data.models.ProfilesResponse
import com.example.profiles.domain.models.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfilesRepository @Inject constructor(
    private val pager: Pager<Int, ProfileEntity>,
    private val dao: ProfilesDao,
    private val connectivityManager: ConnectivityManager
) {
    fun fetchProfiles(): Flow<ProfilesResponse> = flow {
        val networkAvailable = isNetworkAvailable()
        val profilesCount = dao.getProfilesCount()

        when {
            networkAvailable -> {
                emit(ProfilesResponse.Success(getPaginatedProfiles()))
            }
            !networkAvailable && profilesCount > 0 -> {
                emit(ProfilesResponse.Success(getPaginatedProfiles()))
            }
            !networkAvailable && profilesCount == 0 -> {
                emit(ProfilesResponse.ConnectivityError)
            }
        }
    }

    private fun getPaginatedProfiles(): Flow<PagingData<Profile>> = pager.flow.map { pagingData ->
        pagingData.map { profile ->
            Profile(
                id = profile.id,
                name = profile.name,
                email = profile.email,
                birthDate = profile.birthDate,
                phone = profile.phone,
                picture = profile.picture,
                country = profile.country,
                gender = profile.gender
            )
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
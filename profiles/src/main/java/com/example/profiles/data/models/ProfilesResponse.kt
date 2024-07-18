package com.example.profiles.data.models

import androidx.paging.PagingData
import com.example.profiles.domain.models.Profile
import kotlinx.coroutines.flow.Flow

sealed class ProfilesResponse {
    data class Success(val profiles: Flow<PagingData<Profile>>) : ProfilesResponse()
    data object ConnectivityError : ProfilesResponse()
}
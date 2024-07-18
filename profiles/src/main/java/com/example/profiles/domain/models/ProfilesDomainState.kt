package com.example.profiles.domain.models

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

sealed class ProfilesDomainState {
    data class Success(val profiles: Flow<PagingData<Profile>>) : ProfilesDomainState()
    data object ConnectivityError : ProfilesDomainState()
}
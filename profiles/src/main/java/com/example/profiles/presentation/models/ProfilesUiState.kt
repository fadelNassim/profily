package com.example.profiles.presentation.models

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

sealed class ProfilesUiState {
    data object NoState : ProfilesUiState()
    data object Loading: ProfilesUiState()
    data class Success(val profiles: Flow<PagingData<ProfileUi>>) : ProfilesUiState()
    data object ShowConnectivityError : ProfilesUiState()
}
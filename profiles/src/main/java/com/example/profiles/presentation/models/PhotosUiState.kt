package com.example.profiles.presentation.models

import androidx.paging.PagingData
import com.example.profiles.presentation.displaymodels.ProfileUi
import kotlinx.coroutines.flow.Flow

sealed class ProfilessUiState {
    data object NoState : ProfilessUiState()

    data object Loading: ProfilessUiState()
    data class Success(val profiles: Flow<PagingData<ProfileUi>>) : ProfilessUiState()
    data object Error : ProfilessUiState()
}
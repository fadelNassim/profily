package com.example.profiles.data.models

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

sealed class ProfilesDataState {
    data class Success(val profiles: Flow<PagingData<Profiles>>) : ProfilesDataState()
    data object Error : ProfilesDataState()
}
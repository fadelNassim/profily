package com.example.profiles.domain.states

import androidx.paging.PagingData
import com.example.profiles.domain.models.Profile

sealed class ProfilesResult {
    data object Error : ProfilesResult()
    data class Success(val profiles: PagingData<Profile>) : ProfilesResult()
}
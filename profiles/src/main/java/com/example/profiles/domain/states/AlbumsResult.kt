package com.example.profiles.domain.states

import androidx.paging.PagingData
import com.example.profiles.domain.models.Profile

sealed class ProfilessResult {
    data object Error : ProfilessResult()
    data class Success(val profiles: PagingData<Profile>) : ProfilessResult()
}
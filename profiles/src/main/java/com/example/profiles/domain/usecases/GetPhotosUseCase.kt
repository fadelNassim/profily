package com.example.profiles.domain.usecases

import com.example.profiles.data.models.ProfilesResponse
import com.example.profiles.data.repositories.ProfilesRepository
import com.example.profiles.domain.models.ProfilesDomainState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProfilesUseCase @Inject constructor(private val repository: ProfilesRepository) {
    operator fun invoke(): Flow<ProfilesDomainState> {
        return repository.fetchProfiles().map {
            when (it) {
                is ProfilesResponse.Success -> ProfilesDomainState.Success(profiles = it.profiles)
                is ProfilesResponse.ConnectivityError -> ProfilesDomainState.ConnectivityError
            }
        }
    }
}
package com.example.profiles.domain.usecases

import androidx.paging.PagingData
import com.example.profiles.data.repositories.ProfilesRepository
import com.example.profiles.domain.models.Profile
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProfilesUseCase @Inject constructor(private val repository: ProfilesRepository) {
    operator fun invoke(): Flow<PagingData<Profile>> {
        return repository.fetchProfiles()
    }
}
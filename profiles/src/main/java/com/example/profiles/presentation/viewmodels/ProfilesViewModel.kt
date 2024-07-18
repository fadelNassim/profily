package com.example.profiles.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.profiles.domain.usecases.GetProfilesUseCase
import com.example.profiles.presentation.displaymodels.ProfileUi
import com.example.profiles.presentation.models.ProfilesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfilesViewModel @Inject constructor(
    private val getProfiles: GetProfilesUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _profilesUiState = MutableStateFlow<ProfilesUiState>(ProfilesUiState.NoState)
    val profilesUiState: StateFlow<ProfilesUiState> = _profilesUiState

    init {
        loadProfiles()
    }

    private fun loadProfiles() {
        _profilesUiState.value = ProfilesUiState.Loading
        viewModelScope.launch(ioDispatcher) {
            val profiles = getProfiles().map { pagingData ->
                pagingData.map { profile ->
                    ProfileUi(
                        id = profile.id,
                        name = profile.name,
                        email = profile.email,
                        birthDate = profile.birthDate,
                        phone = profile.phone,
                        picture = profile.picture,
                        country = profile.country,
                        isMale = profile.gender.isMale(),
                    )
                }
            }.cachedIn(viewModelScope)
            _profilesUiState.value = profiles.let { ProfilesUiState.Success(profiles) }
        }
    }

    private fun String.isMale() = this == "male"
}
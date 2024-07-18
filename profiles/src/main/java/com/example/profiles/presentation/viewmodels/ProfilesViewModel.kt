package com.example.profiles.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.example.profiles.domain.models.ProfilesDomainState
import com.example.profiles.domain.usecases.GetProfilesUseCase
import com.example.profiles.presentation.displaymodels.ProfileUi
import com.example.profiles.presentation.models.ProfilesUiState
import com.example.profiles.presentation.models.ProfilesUiState.Loading
import com.example.profiles.presentation.models.ProfilesUiState.NoState
import com.example.profiles.presentation.models.ProfilesUiState.ShowConnectivityError
import com.example.profiles.presentation.models.ProfilesUiState.Success
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
    private val _profilesUiState = MutableStateFlow<ProfilesUiState>(NoState)
    val profilesUiState: StateFlow<ProfilesUiState> = _profilesUiState

    init {
        loadProfiles()
    }

    fun loadProfiles() {
        _profilesUiState.value = Loading
        viewModelScope.launch(ioDispatcher) {
            getProfiles().collect { domainState ->
                _profilesUiState.value = when (domainState) {
                    is ProfilesDomainState.Success -> Success(
                        profiles = domainState.profiles.map { pagingData ->
                            pagingData.map { profile ->
                                ProfileUi(
                                    id = profile.id,
                                    name = profile.name,
                                    email = profile.email,
                                    birthDate = profile.birthDate,
                                    phone = profile.phone,
                                    picture = profile.picture,
                                    country = profile.country,
                                    isMale = profile.gender.isMale()
                                )
                            }
                        }
                    )

                    is ProfilesDomainState.ConnectivityError -> ShowConnectivityError
                }
            }
        }
    }

    private fun String.isMale() = this == "male"
}
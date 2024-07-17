package com.example.profiles.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.profiles.domain.usecases.GetProfilesUseCase
import com.example.profiles.presentation.displaymodels.ProfileUi
import com.example.profiles.presentation.models.ProfilessUiState
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
    private val _profilesUiState = MutableStateFlow<ProfilessUiState>(ProfilessUiState.NoState)
    val profilesUiState: StateFlow<ProfilessUiState> = _profilesUiState

    init {
        loadProfiless()
    }

    fun loadProfiless() {
        _profilesUiState.value = ProfilessUiState.Loading
        viewModelScope.launch(ioDispatcher) {
            val profiles = getProfiles().map { pagingData ->
                pagingData.map {
                    ProfileUi(
                        name = it.name,
                        email = it.email,
                        phone = it.phone,
                        picture = it.picture,
                        dob = it.dob,
                        id = it.id
                    )
                }
            }.cachedIn(viewModelScope)
            _profilesUiState.value = profiles.let { ProfilessUiState.Success(it) }
        }
    }
}
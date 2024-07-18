package com.example.profiles.presentation.viewmodels

import androidx.paging.PagingData
import com.example.profiles.domain.models.Profile
import com.example.profiles.domain.models.ProfilesDomainState
import com.example.profiles.domain.usecases.GetProfilesUseCase
import com.example.profiles.presentation.isMale
import com.example.profiles.presentation.models.ProfilesUiState
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProfilesViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    private lateinit var getProfilesUseCase: GetProfilesUseCase
    private lateinit var viewModel: ProfilesViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        getProfilesUseCase = mockk()
        viewModel = ProfilesViewModel(getProfilesUseCase, dispatcher)
    }

    @Test
    fun `loadProfiles sets profilesUiState to Success when getProfilesUseCase returns Success`() = runTest {
        // Given
        val profiles = listOf(
            Profile(
                id = 1,
                name = "Jean Louis",
                email = "test@test.com",
                birthDate = "2000-01-01",
                phone = "1234567890",
                picture = "Test",
                country = "Test",
                gender = "Test"
            )
        )
        val pagingData = PagingData.from(profiles)

        every { getProfilesUseCase.invoke() } returns flowOf(
            ProfilesDomainState.Success(
                flowOf(
                    pagingData
                )
            )
        )

        // When
        viewModel.loadProfiles()
        advanceUntilIdle()

        // Then
        assert(viewModel.profilesUiState.value is ProfilesUiState.Success)
    }

    @Test
    fun `loadProfiles sets profilesUiState to ShowConnectivityError when getProfilesUseCase returns ConnectivityError`() =
        runTest {
            // Given
            every { getProfilesUseCase.invoke() } returns flowOf(ProfilesDomainState.ConnectivityError)

            // When
            viewModel.loadProfiles()
            advanceUntilIdle()

            // Then
            assert(viewModel.profilesUiState.value is ProfilesUiState.ShowConnectivityError)
        }

    @Test
    fun `isMale returns true when gender is male`() {
        val maleString = "male"
        assertTrue(maleString.isMale())
    }

    @Test
    fun `isMale returns false when gender is not male`() {
        val notMaleString = "female"
        assertFalse(notMaleString.isMale())
    }
}
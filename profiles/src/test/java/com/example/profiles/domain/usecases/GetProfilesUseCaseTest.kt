package com.example.profiles.domain.usecases

import androidx.paging.PagingData
import com.example.profiles.data.models.ProfilesResponse
import com.example.profiles.data.repositories.ProfilesRepository
import com.example.profiles.domain.models.Profile
import com.example.profiles.domain.models.ProfilesDomainState
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetProfilesUseCaseTest {
    private lateinit var repository: ProfilesRepository
    private lateinit var getProfiles: GetProfilesUseCase

    @Before
    fun setup() {
        repository = mockk()
        getProfiles = GetProfilesUseCase(repository)
    }

    @Test
    fun `invoke returns Success when repository returns Success`() = runTest {
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
        every { repository.fetchProfiles() } returns flowOf(
            ProfilesResponse.Success(
                flowOf(
                    pagingData
                )
            )
        )

        // When
        val result = getProfiles().first()

        // Then
        assertTrue(
            result is ProfilesDomainState.Success
        )
    }

    @Test
    fun `invoke returns ConnectivityError when repository returns ConnectivityError`() = runTest {
        // Given
        every { repository.fetchProfiles() } returns flowOf(ProfilesResponse.ConnectivityError)

        // When
        val result = getProfiles().first()

        // Then
        assertTrue(result is ProfilesDomainState.ConnectivityError)
    }
}
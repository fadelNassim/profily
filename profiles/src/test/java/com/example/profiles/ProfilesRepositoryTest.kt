package com.example.profiles

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.paging.Pager
import com.example.profiles.data.db.dao.ProfilesDao
import com.example.profiles.data.db.entities.ProfileEntity
import com.example.profiles.data.models.ProfilesResponse
import com.example.profiles.data.repositories.ProfilesRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProfilesRepositoryTest {
    private lateinit var dao: ProfilesDao
    private lateinit var pager: Pager<Int, ProfileEntity>
    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var networkCapabilities: NetworkCapabilities
    private lateinit var network: Network

    private lateinit var repository: ProfilesRepository

    @Before
    fun setup() {
        dao = mockk()
        pager = mockk(relaxed = true)
        connectivityManager = mockk()
        networkCapabilities = mockk()
        network = mockk()

        every { connectivityManager.activeNetwork } returns network
        every { connectivityManager.getNetworkCapabilities(network) } returns networkCapabilities
        repository = ProfilesRepository(pager, dao, connectivityManager)
    }

    @Test
    fun `fetchProfiles returns Success when network is available and db has data`() = runTest {
        // Given
        every { networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) } returns true
        coEvery { dao.getProfilesCount() } returns 1

        // When
        val result = repository.fetchProfiles().toList().first()

        // Then
        Assert.assertTrue(result is ProfilesResponse.Success)
    }

    @Test
    fun `fetchProfiles returns Success when network is not available and db has data`() = runTest {
        // Given
        every { networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) } returns false
        coEvery { dao.getProfilesCount() } returns 1

        // When
        val result = repository.fetchProfiles().toList().first()

        // Then
        Assert.assertTrue(result is ProfilesResponse.Success)
    }

    @Test
    fun `fetchProfiles returns ConnectivityError when network is not available and db is empty`() = runTest {
        // Given
        every { networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) } returns false
        coEvery { dao.getProfilesCount() } returns 0

        // When
        val result = repository.fetchProfiles().toList().first()

        // Then
        Assert.assertTrue(result is ProfilesResponse.ConnectivityError)
    }
}
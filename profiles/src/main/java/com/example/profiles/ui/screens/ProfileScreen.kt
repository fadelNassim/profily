package com.example.profiles.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.profiles.presentation.displaymodels.ProfileUi
import com.example.profiles.presentation.models.ProfilesUiState.*
import com.example.profiles.presentation.viewmodels.ProfilesViewModel
import com.example.profiles.ui.components.ProfileCard

@Composable
fun ProfilesScreen(appPadding: PaddingValues) {
    val viewModel: ProfilesViewModel = hiltViewModel()

    when (val state = viewModel.profilesUiState.collectAsState().value) {
        is Success -> {
            val profiles = state.profiles.collectAsLazyPagingItems()
            PagedProfiles(profiles = profiles, appPadding)
        }
        Loading, NoState -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color(0xFFF56B2A))
            }
        }
        Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error loading profiles. Please try again later.")
            }
        }
    }
}

@Composable
fun PagedProfiles(profiles: LazyPagingItems<ProfileUi>, appPadding: PaddingValues) {
    LazyColumn(
        modifier = Modifier.padding(appPadding),
    ) {
        items(
            count = profiles.itemCount,
            key = profiles.itemKey { item -> item.id },
            contentType = profiles.itemContentType{"profiles"}
        ) { index ->
            profiles[index]?.let {
                    profile ->
                ProfileCard(
                    profile = profile,
                )
            }
        }
    }
}
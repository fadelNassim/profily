package com.example.profiles.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.profiles.R
import com.example.profiles.presentation.models.ProfileUi
import com.example.profiles.presentation.models.ProfilesUiState
import com.example.profiles.presentation.models.ProfilesUiState.Loading
import com.example.profiles.presentation.models.ProfilesUiState.NoState
import com.example.profiles.presentation.models.ProfilesUiState.Success
import com.example.profiles.presentation.viewmodels.ProfilesViewModel
import com.example.profiles.ui.components.ErrorWithRetryButton
import com.example.profiles.ui.components.ProfileCard

@Composable
fun ProfilesScreen(appPadding: PaddingValues) {
    val viewModel: ProfilesViewModel = hiltViewModel()

    when (val uiState = viewModel.profilesUiState.collectAsState().value) {
        is Success -> {
            val profiles = uiState.profiles.collectAsLazyPagingItems()
            PagedProfiles(profiles = profiles, appPadding)
        }

        Loading, NoState -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        ProfilesUiState.ShowConnectivityError -> {
            ErrorWithRetryButton(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(appPadding)
                    .padding(horizontal = 24.dp),
                onClick = { viewModel.loadProfiles() },
                message = stringResource(id = R.string.network_error)
            )
            }
        }
}

@Composable
fun PagedProfiles(
    profiles: LazyPagingItems<ProfileUi>,
    appPadding: PaddingValues
) {
    if (profiles.loadState.refresh is LoadState.Loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier.padding(appPadding),
        ) {
            items(
                count = profiles.itemCount,
                key = profiles.itemKey { item -> item.id },
                contentType = profiles.itemContentType { "profiles" }
            ) { index ->
                profiles[index]?.let { profile ->
                    ProfileCard(
                        profile = profile,
                    )
                }
            }
            item {
                if (profiles.loadState.append is LoadState.Loading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.scale(0.5f)
                        )
                    }
                }
            }
            item {
                if (profiles.loadState.hasError) {
                    ErrorWithRetryButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 16.dp),
                        onClick = { profiles.retry() },
                        message = stringResource(id = R.string.network_error)
                    )
                }
            }
        }
    }
}
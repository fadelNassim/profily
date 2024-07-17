package com.example.profily.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.profiles.ui.screens.ProfilessScreen

sealed class Screen(val route: String) {
    data object ProfilesScreen : Screen("profile_screen")
}

@Composable
fun AppNavigation(appPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ProfilesScreen.route) {
        composable(Screen.ProfilesScreen.route) {
            ProfilessScreen(appPadding)
        }
    }
}
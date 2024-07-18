package com.example.profily.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.profiles.ui.screens.ProfilesScreen

sealed class Screen(val route: String) {
    data object ProfileScreen : Screen("profile_screen")
}

@Composable
fun AppNavigation(appPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ProfileScreen.route) {
        composable(Screen.ProfileScreen.route) {
            ProfilesScreen(appPadding)
        }
    }
}
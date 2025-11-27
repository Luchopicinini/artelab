package com.localgo.artelab.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.localgo.artelab.ui.screens.HomeScreen
import com.localgo.artelab.ui.screens.ProfileScreen
import com.localgo.artelab.ui.screens.LoginScreen
import com.localgo.artelab.viewmodel.HomeViewModel

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // LOGIN
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home")
                }
            )
        }

        // HOME
        composable("home") {
            val homeViewModel = HomeViewModel()
            HomeScreen(
                viewModel = homeViewModel,
                onProfileClick = {
                    navController.navigate("profile")
                }
            )
        }

        // PROFILE
        composable("profile") {
            ProfileScreen()
        }
    }
}

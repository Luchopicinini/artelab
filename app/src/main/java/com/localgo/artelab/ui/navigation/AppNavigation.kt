package com.localgo.artelab.ui.navigation

import androidx.compose.runtime.Composable //funciones ui de compose
import androidx.navigation.compose.NavHost//contenedor de navegación en compose
import androidx.navigation.compose.composable //declara una pantalla y ruta del navHost
import androidx.navigation.compose.rememberNavController //crea y recuerda
import com.localgo.artelab.ui.screens.LoginScreen
import com.localgo.artelab.ui.screens.HomeScreen
import com.localgo.artelab.ui.screens.ProfileScreen

@Composable
fun AppNavigation() { //funcion de UI que define la navegación
    val navController = rememberNavController() //crea y recuerda NavController

    NavHost(//crea marco que organiza y muestra las pantallas
        navController = navController,
        startDestination = "login"
    ) {
        // pantalla de login  lleva al home
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") }
            )
        }

        // pantalla home lleva al perfil
        composable("home") {
            HomeScreen(
                onProfileClick = { navController.navigate("profile") }
            )
        }

        // pantalla de perfil al botón para volver
        composable("profile") {
            ProfileScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

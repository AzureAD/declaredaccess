package io.eyeonit.eyeonit.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.eyeonit.eyeonit.ui.WelcomeScreen

fun NavGraphBuilder.introGraph(navController: NavController) {
    navigation(startDestination = UnprotectedRoutesNav.WELCOME_SCREEN, route = UnprotectedRoutesNav.ROUTE) {
        composable(UnprotectedRoutesNav.WELCOME_SCREEN) {
            WelcomeScreen(navController)
        }
    }
}
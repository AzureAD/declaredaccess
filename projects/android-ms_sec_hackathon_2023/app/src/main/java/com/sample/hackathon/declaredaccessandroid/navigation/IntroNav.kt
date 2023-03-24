package com.sample.hackathon.declaredaccessandroid.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.sample.hackathon.declaredaccessandroid.ui.WelcomeScreen

fun NavGraphBuilder.unprotectedRoutesGraph(navController: NavController) {
    navigation(startDestination = UnprotectedRoutesNav.WELCOME_SCREEN, route = UnprotectedRoutesNav.ROUTE) {
        composable(UnprotectedRoutesNav.WELCOME_SCREEN) {
            WelcomeScreen()
        }
    }
}
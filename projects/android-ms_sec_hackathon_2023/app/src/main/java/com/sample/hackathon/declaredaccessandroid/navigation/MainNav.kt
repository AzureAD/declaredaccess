package com.sample.hackathon.declaredaccessandroid.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.sample.hackathon.declaredaccessandroid.ui.HomeScreen
import com.sample.hackathon.declaredaccessandroid.ui.SettingsScreen

fun NavGraphBuilder.protectedRoutesGraph(navController: NavController) {
    navigation(startDestination = ProtectedRoutesNav.HOME_SCREEN, route = ProtectedRoutesNav.ROUTE) {
        composable(ProtectedRoutesNav.HOME_SCREEN){
            HomeScreen(navController)
        }
        composable(ProtectedRoutesNav.SETTINGS_SCREEN){
            SettingsScreen(navController)
        }
    }
}


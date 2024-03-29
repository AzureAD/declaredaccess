package com.sample.hackathon.declaredaccessandroid.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.sample.hackathon.declaredaccessandroid.ui.HomeScreen
import com.sample.hackathon.declaredaccessandroid.ui.ProfileScreen

fun NavGraphBuilder.protectedRoutesGraph(navController: NavHostController) {
    navigation(startDestination = ProtectedRoutesNav.HOME_SCREEN, route = ProtectedRoutesNav.ROUTE) {
        composable(ProtectedRoutesNav.HOME_SCREEN){
            HomeScreen(navController)
        }
        composable(ProtectedRoutesNav.PROFILE_SCREEN){
            ProfileScreen(navController)
        }
    }
}
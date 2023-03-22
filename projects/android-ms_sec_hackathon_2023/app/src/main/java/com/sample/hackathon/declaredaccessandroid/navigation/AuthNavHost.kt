package com.sample.hackathon.declaredaccessandroid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sample.hackathon.declaredaccessandroid.msal.MsalPublicClientFactory
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.withContext

@Composable
fun AuthNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    route: String? = null,
    splashScreenId: String,
    splashScreen: NavGraphBuilder.() -> Unit,
    protectedRoutesRootId: String,
    protectedRoutes: NavGraphBuilder.() -> Unit,
    unprotectedRoutesRootId: String,
    unprotectedRoutes: NavGraphBuilder.() -> Unit
) {
    LaunchedEffect(Unit) {
        withContext(IO) {
            val isLoggedIn = MsalPublicClientFactory.isUserLoggedIn()
            withContext(Main) {
                if (isLoggedIn) {
                    navController.navigateAndReplaceStartRoute(protectedRoutesRootId)
                } else {
                    navController.navigateAndReplaceStartRoute(unprotectedRoutesRootId)
                }
            }
        }
    }

    NavHost(
        navController,
        startDestination = splashScreenId,
        modifier = modifier,
        route = route,
        builder = {
            splashScreen()
            protectedRoutes()
            unprotectedRoutes()
        }
    )
}

fun NavHostController.navigateAndReplaceStartRoute(newHomeRoute: String) {
    popBackStack(graph.startDestinationId, true)
    graph.setStartDestination(newHomeRoute)
    navigate(newHomeRoute)
}
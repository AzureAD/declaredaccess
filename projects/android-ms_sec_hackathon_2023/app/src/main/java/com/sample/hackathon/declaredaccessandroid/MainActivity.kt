package com.sample.hackathon.declaredaccessandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.microsoft.identity.client.ISingleAccountPublicClientApplication
import com.sample.hackathon.declaredaccessandroid.http.InterceptorFactory
import com.sample.hackathon.declaredaccessandroid.msal.MsalPublicClientFactory
import com.sample.hackathon.declaredaccessandroid.navigation.*
import com.sample.hackathon.declaredaccessandroid.ui.InteractionRequiredScreen
import com.sample.hackathon.declaredaccessandroid.ui.SplashScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivityNew : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
//                // TODO introduce a global app state (e.g. ViewModel), which the interceptor
//                // interacts with, and which controls the AuthNavHost state.
//                var msalSDK: ISingleAccountPublicClientApplication? by remember { mutableStateOf(null) }
//                LaunchedEffect(Unit) {
//                    withContext(Dispatchers.IO) {
//                        // TODO use DI
//                        msalSDK = MsalPublicClientFactory.init(this@MainActivity, navController)
//                    }
//                }

                val navController = rememberNavController()
                // TODO make this nicer through global dependency injection
                InterceptorFactory.setNavHostController(navController)

                Scaffold(
                    topBar = { AppBar() }
                ) { innerPadding ->
                    AuthNavHost(
                        navController,
                        modifier = Modifier.padding(innerPadding),
                        splashScreenId = RootNav.SPLASH_SCREEN,
                        splashScreen = {
                            composable(RootNav.SPLASH_SCREEN) {
                                SplashScreen()
                            }
                        },
                        protectedRoutesRootId = ProtectedRoutesNav.ROUTE,
                        protectedRoutes = { protectedRoutesGraph(navController) },
                        unprotectedRoutesRootId = UnprotectedRoutesNav.ROUTE,
                        unprotectedRoutes = { unprotectedRoutesGraph(navController) },
                        interactionRequiredScreenId = RootNav.INTERACTION_REQUIRED,
                        interactionRequiredScreen = {
                            composable(RootNav.INTERACTION_REQUIRED) {
                                InteractionRequiredScreen(navController)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun AppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Outlined.Visibility,
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        },
        title = {
            Text(text = "Keep an eye on it")
        },
        backgroundColor = MaterialTheme.colors.primarySurface
    )
}
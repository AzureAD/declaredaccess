package com.sample.hackathon.declaredaccessandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sample.hackathon.declaredaccessandroid.navigation.*
import com.sample.hackathon.declaredaccessandroid.ui.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                val navController = rememberNavController()
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
                        unprotectedRoutes = { unprotectedRoutesGraph(navController) }
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
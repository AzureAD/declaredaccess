package io.eyeonit.eyeonit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.eyeonit.eyeonit.navigation.AuthNavHost
import io.eyeonit.eyeonit.navigation.ProtectedRoutesNav
import io.eyeonit.eyeonit.navigation.UnprotectedRoutesNav
import io.eyeonit.eyeonit.navigation.RootNav
import io.eyeonit.eyeonit.navigation.unprotectedRoutesGraph
import io.eyeonit.eyeonit.navigation.protectedRoutesGraph
import io.eyeonit.eyeonit.ui.SplashScreen

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
package io.eyeonit.eyeonit

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.eyeonit.eyeonit.ui.mailbox.EmailWatchable
import io.eyeonit.eyeonit.ui.theme.EyeonitTheme
import io.eyeonit.eyeonit.ui.watchables.WatchablesBody

@Composable
fun Home() {
    EyeonitTheme {
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        Scaffold(
            topBar = { AppBar() }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                        startDestination = "Watchables",
                Modifier.padding(innerPadding)
            ) {
                composable("Watchables"){
                    WatchablesBody(
                        onWatchableClick = { name ->
                            navController.navigate(name);
                        }
                    )
                }
                composable("mailbox"){
                    EmailWatchable(title = "keep an eye on a mailbox")
                }
            }
        }
    }
}

@Composable
private fun AppBar() {11
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
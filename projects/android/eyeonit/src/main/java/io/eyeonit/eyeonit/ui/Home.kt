package io.eyeonit.eyeonit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.eyeonit.eyeonit.navigation.ProtectedRoutesNav

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home")
        Button(onClick = {

        }, modifier = Modifier.padding(16.dp)) {
            Text("Log out")
        }
        Button(onClick = {
            navController.navigate(ProtectedRoutesNav.SETTINGS_SCREEN)
        }, modifier = Modifier.padding(16.dp)) {
            Text("Settings")
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}
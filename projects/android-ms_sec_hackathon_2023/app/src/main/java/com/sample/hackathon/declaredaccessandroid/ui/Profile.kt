package com.sample.hackathon.declaredaccessandroid.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sample.hackathon.declaredaccessandroid.graph.GraphServiceFactory
import com.sample.hackathon.declaredaccessandroid.graph.dto.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Profile Overview",
            modifier = Modifier.padding(20.dp),
            fontSize = 20.sp
        )
        var userProfile: User? by remember { mutableStateOf(null) }
        LaunchedEffect(Unit) {
            withContext(IO) {
                val userCall =
                    GraphServiceFactory.getInstance().me
                userProfile = userCall.execute().body()
            }
        }

        Text("User: " + userProfile?.displayName, modifier = Modifier.padding(20.dp))
        Button(onClick = {
            navController.popBackStack()
        }, modifier = Modifier.padding(20.dp)) {
            Text("Go Back")
        }
    }
}

@Preview
@Composable
fun MotivationPreview() {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}
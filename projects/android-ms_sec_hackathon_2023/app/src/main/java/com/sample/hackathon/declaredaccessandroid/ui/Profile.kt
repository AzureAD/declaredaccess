package com.sample.hackathon.declaredaccessandroid.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.sample.hackathon.declaredaccessandroid.msal.exception.NoSignedInUserException
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

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
                try {
                userProfile = userCall.execute().body()
                } catch (e: NoSignedInUserException) {
                    Log.d("ProfileScreen", "No signed in user.")
                }
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
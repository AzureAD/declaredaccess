package com.sample.hackathon.declaredaccessandroid.ui

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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.microsoft.identity.client.ISingleAccountPublicClientApplication.SignOutCallback
import com.microsoft.identity.client.exception.MsalException
import com.sample.hackathon.declaredaccessandroid.msal.MsalPublicClientFactory
import com.sample.hackathon.declaredaccessandroid.navigation.ProtectedRoutesNav
import com.sample.hackathon.declaredaccessandroid.navigation.navigateToUnprotectedRoutesRoot

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home",
            modifier = Modifier.padding(20.dp),
            fontSize = 20.sp
        )
        Button(onClick = {
            navController.navigate(ProtectedRoutesNav.PROFILE_SCREEN)
        }, modifier = Modifier.padding(16.dp)) {
            Text("Profile")
        }
        Button(onClick = {
            logOut(navController)
        }, modifier = Modifier.padding(16.dp)) {
            Text("Log out")
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}

private fun logOut(navController: NavHostController) {
    MsalPublicClientFactory.signOut(object : SignOutCallback {
        override fun onSignOut() {
            navController.navigateToUnprotectedRoutesRoot()
        }

        override fun onError(exception: MsalException) {

        }
    })
}
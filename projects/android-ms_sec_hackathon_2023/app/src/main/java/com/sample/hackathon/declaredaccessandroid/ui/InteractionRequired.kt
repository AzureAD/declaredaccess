package com.sample.hackathon.declaredaccessandroid.ui

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.microsoft.identity.client.AcquireTokenParameters
import com.microsoft.identity.client.AuthenticationCallback
import com.microsoft.identity.client.IAuthenticationResult
import com.microsoft.identity.client.exception.MsalException
import com.sample.hackathon.declaredaccessandroid.msal.MsalPublicClientFactory

@Composable
fun InteractionRequiredScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val activity = LocalContext.current as Activity

        Text("Oops!  We ran into a problem trying to get a token. Let's try and fix it!",
            textAlign = TextAlign.Center, modifier = Modifier.padding(16.dp)
        )
        Button(onClick = {
            signIn(activity, navController)
        }, modifier = Modifier.padding(16.dp)) {
            Text("Fix it 👌")
        }
    }
}

@Preview
@Composable
fun InteractionRequired() {
    SplashScreen()
}

private fun signIn(activity: Activity, navController: NavController) {
    MsalPublicClientFactory.acquireTokenInteractively(activity, object : AuthenticationCallback {
        override fun onSuccess(authenticationResult: IAuthenticationResult) {
            Log.d("Msal", "Successfully authenticated")
            navController.popBackStack()
        }

        override fun onError(exception: MsalException) {
            Log.e("Msal", "Authentication failed: ${exception.message}", exception)
        }

        override fun onCancel() {
            Log.d("Msal", "User cancelled login.")
        }
    })
}
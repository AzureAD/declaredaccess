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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.microsoft.identity.client.AuthenticationCallback
import com.microsoft.identity.client.IAuthenticationResult
import com.microsoft.identity.client.SignInParameters
import com.microsoft.identity.client.exception.MsalException
import com.sample.hackathon.declaredaccessandroid.msal.MsalPublicClientFactory
import com.sample.hackathon.declaredaccessandroid.navigation.navigateToProtectedRoutesRoot

@Composable
fun WelcomeScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val activity = LocalContext.current as Activity

        Text(text = "Welcome",
            modifier = Modifier.padding(20.dp),
            fontSize = 20.sp
        )
        Button(onClick = {
            signIn(activity, navHostController)
        }, modifier = Modifier.padding(16.dp)) {
            Text("Log in")
        }
    }
}

@Preview
@Composable
fun WelcomePreview() {
    val navController = rememberNavController()
    WelcomeScreen(navController)
}

private fun signIn(activity: Activity, navHostController: NavHostController) {
    val signInParameters = SignInParameters
        .builder()
        .withActivity(activity)
        .withScope("https://graph.microsoft.com/.default")
        .withCallback(object :
            AuthenticationCallback {
            override fun onCancel() { }

            override fun onSuccess(authenticationResult: IAuthenticationResult) {
                Log.d("Welcome", "Successfully signed in")
                navHostController.navigateToProtectedRoutesRoot()
            }

            override fun onError(exception: MsalException) {}
        })
        .build()

    MsalPublicClientFactory.signIn(signInParameters)
}
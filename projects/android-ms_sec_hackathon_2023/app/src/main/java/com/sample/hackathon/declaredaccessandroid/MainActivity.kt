package com.sample.hackathon.declaredaccessandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // See MsalPublicClientFactory for a convenience function to init MSAL
        // See GraphServiceFactory for convenience functions to call Graph

    }
}
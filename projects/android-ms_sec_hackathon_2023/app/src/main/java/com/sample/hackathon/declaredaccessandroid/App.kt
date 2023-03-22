package com.sample.hackathon.declaredaccessandroid

import android.app.Application
import com.sample.hackathon.declaredaccessandroid.msal.MsalPublicClientFactory

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialise global MSAL
        Thread {
            MsalPublicClientFactory.init(this)
        }.start()
    }
}
package io.swath.microsoft.security

import android.app.Activity
import android.util.Log
import androidx.fragment.app.Fragment
import io.swath.microsoft.security.annotations.Feature

interface SecurityGate {


    fun checkAccess(){
        this.takeIf { it is Activity}?.apply {
            var activity = this as Activity
            val feature = activity.javaClass.kotlin.annotations.find { it is Feature } as? Feature
            Log.d("SecurityGate", "checkAccess: ${feature?.name}")
        }

        this.takeIf { it is Fragment }?.apply {
            var fragment = this as Fragment
        }
    }

}
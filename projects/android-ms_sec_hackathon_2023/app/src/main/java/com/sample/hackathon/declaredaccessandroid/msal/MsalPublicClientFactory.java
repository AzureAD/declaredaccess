package com.sample.hackathon.declaredaccessandroid.msal;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

import com.sample.hackathon.declaredaccessandroid.R;
import com.microsoft.identity.client.IPublicClientApplication;
import com.microsoft.identity.client.PublicClientApplication;

/**
 * Convenience class for constructing instances of MSAL-Android.
 */
public class MsalPublicClientFactory {

    @WorkerThread
    public static void createPublicClientApplication(
            @NonNull Context context,
            @NonNull IPublicClientApplication.ISingleAccountApplicationCreatedListener listener) {
        PublicClientApplication.createSingleAccountPublicClientApplication(
                context,
                R.raw.auth_config_declared_access_single_acct,
                listener
        );
    }
}

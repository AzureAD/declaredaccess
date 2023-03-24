package com.sample.hackathon.declaredaccessandroid.msal;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;

import com.microsoft.identity.client.AcquireTokenParameters;
import com.microsoft.identity.client.IAccount;
import com.microsoft.identity.client.ISingleAccountPublicClientApplication;
import com.microsoft.identity.client.PublicClientApplication;
import com.microsoft.identity.client.SignInParameters;
import com.microsoft.identity.client.exception.MsalException;
import com.microsoft.identity.common.java.util.ResultFuture;
import com.sample.hackathon.declaredaccessandroid.R;
import com.sample.hackathon.declaredaccessandroid.graph.GraphServiceFactory;

import java.util.concurrent.ExecutionException;

/**
 * Convenience class for constructing instances of MSAL-Android.
 */
public class MsalPublicClientFactory {

    public static ISingleAccountPublicClientApplication init(
            @NonNull Context context)  {
        if (instance == null) {
            try {
                instance = createPublicClientApplication(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private static ISingleAccountPublicClientApplication instance;

    public static ISingleAccountPublicClientApplication getInstance() {
        if (instance == null) {
            throw new IllegalArgumentException("MSAL must be initialised first");
        }
        return instance;
    }

    @WorkerThread
    public static ISingleAccountPublicClientApplication createPublicClientApplication(
            @NonNull Context context) throws ExecutionException, InterruptedException {
        final ResultFuture<ISingleAccountPublicClientApplication> future = new ResultFuture<>();
        PublicClientApplication.createSingleAccountPublicClientApplication(
                context,
                R.raw.auth_config_declared_access_single_acct,
                new PublicClientApplication.ISingleAccountApplicationCreatedListener() {
                    @Override
                    public void onCreated(ISingleAccountPublicClientApplication application) {
                        instance = application;
                        future.setResult(application);
                        GraphServiceFactory.init(instance);
                    }

                    @Override
                    public void onError(MsalException exception) {
                        future.setException(exception);
                    }
                });


        return future.get();
    }

    public static boolean isUserLoggedIn() throws InterruptedException, ExecutionException {
        final ResultFuture<Boolean> future = new ResultFuture<>();

        getInstance().getCurrentAccountAsync(new ISingleAccountPublicClientApplication.CurrentAccountCallback() {
            @Override
            public void onAccountLoaded(@Nullable IAccount activeAccount) {
                future.setResult(activeAccount != null);
            }

            @Override
            public void onAccountChanged(@Nullable IAccount priorAccount, @Nullable IAccount currentAccount) {
                Log.e("MsalPublicClientFactory", "onAccountChanged: " + priorAccount + " " + currentAccount);
            }

            @Override
            public void onError(@NonNull MsalException exception) {
                Log.e("MsalPublicClientFactory", "onError", exception);
            }
        });

        return future.get();
    }

    public static void signIn(SignInParameters parameters) {
        getInstance().signIn(parameters);
    }

    public static void acquireTokenInteractively(AcquireTokenParameters parameters) {
        getInstance().acquireToken(parameters);
    }
}

package com.sample.hackathon.declaredaccessandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.microsoft.identity.client.AuthenticationCallback;
import com.microsoft.identity.client.IAuthenticationResult;
import com.microsoft.identity.client.IPublicClientApplication;
import com.microsoft.identity.client.ISingleAccountPublicClientApplication;
import com.microsoft.identity.client.SignInParameters;
import com.microsoft.identity.client.exception.MsalException;
import com.sample.hackathon.declaredaccessandroid.graph.GraphServiceFactory;
import com.sample.hackathon.declaredaccessandroid.graph.dto.User;
import com.sample.hackathon.declaredaccessandroid.msal.MsalPublicClientFactory;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Try creating MSAL....
        new Thread(new Runnable() {
            @Override
            public void run() {
                MsalPublicClientFactory.createPublicClientApplication(
                        MainActivity.this,
                        new IPublicClientApplication.ISingleAccountApplicationCreatedListener() {
                            @Override
                            public void onCreated(final ISingleAccountPublicClientApplication application) {
                                MainActivity.this.runOnUiThread(
                                        new Runnable() {
                                            @Override
                                            public void run() {
                                                final SignInParameters signInParameters =
                                                        SignInParameters
                                                                .builder()
                                                                .withActivity(MainActivity.this)
                                                                .withScope("https://graph.microsoft.com/.default")
                                                                .withCallback(new AuthenticationCallback() {
                                                                    @Override
                                                                    public void onCancel() {
                                                                        Log.d("TAG", "Cancel!");
                                                                    }

                                                                    @Override
                                                                    public void onSuccess(final IAuthenticationResult authenticationResult) {
                                                                        new Thread(new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                Log.d("TAG", "Success: " + authenticationResult.getAccessToken());
                                                                                Call<User> userCall = GraphServiceFactory.createDefaultGraphService(application).getMe();
                                                                                try {
                                                                                    Response<User> response = userCall.execute();
                                                                                    User user = response.body();
                                                                                } catch (IOException e) {
                                                                                    throw new RuntimeException(e);
                                                                                }
                                                                            }
                                                                        }).start();
                                                                    }

                                                                    @Override
                                                                    public void onError(MsalException exception) {
                                                                        Log.d("TAG", "Error! " + Log.getStackTraceString(exception));
                                                                    }
                                                                })
                                                                .build();
                                                application.signIn(signInParameters);
                                            }
                                        }
                                );
                            }

                            @Override
                            public void onError(MsalException exception) {

                            }
                        }
                );
            }
        }).start();
    }
}
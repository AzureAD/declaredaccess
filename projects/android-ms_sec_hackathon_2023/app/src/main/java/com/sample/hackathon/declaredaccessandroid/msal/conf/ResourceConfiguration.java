package com.sample.hackathon.declaredaccessandroid.msal.conf;

import androidx.annotation.NonNull;

import java.net.MalformedURLException;
import java.net.URL;

public class ResourceConfiguration {

    private final String mEndpoint;
    private final String mAadAppId;

    public ResourceConfiguration(@NonNull final String endpoint,
                                 @NonNull final String aadAppId) {
        mEndpoint = endpoint;
        mAadAppId = aadAppId;
    }

    public String getEndpoint() {
        return mEndpoint;
    }

    private static final URL toUrl(@NonNull final String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            // It's a hackathon! YOLO :P
            throw new RuntimeException(e);
        }
    }

    public URL getEndpointUrl() {
        return toUrl(getEndpoint());
    }

    public String getAadAppId() {
        return mAadAppId;
    }

    public boolean hostNameMatches(@NonNull final URL inputUrl) {
        return getEndpointUrl().getHost().equals(inputUrl.getHost());
    }

    public boolean hostNameMatches(@NonNull final String inputUrl) {
        return getEndpointUrl()
                .getHost()
                .equals(toUrl(inputUrl).getHost());
    }

    public String getDefaultScope() {
        return "api://" + mAadAppId + "/.default";
    }
}

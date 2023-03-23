package com.sample.hackathon.declaredaccessandroid.msal.exception;

import java.io.IOException;

/**
 * Subclass {@link IOException} to allow us to send this through our interceptor.
 */
public class UiRequiredRequiredException extends IOException {

    public UiRequiredRequiredException(Throwable cause) {
        super(cause);
    }
}

package com.sample.hackathon.declaredaccessandroid.msal.exception;

import java.io.IOException;

public class NoSignedInUserException extends IOException {

    public NoSignedInUserException(String message) {
        super(message);
    }
}

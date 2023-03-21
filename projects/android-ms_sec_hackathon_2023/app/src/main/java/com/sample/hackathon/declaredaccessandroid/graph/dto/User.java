package com.sample.hackathon.declaredaccessandroid.graph.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Simplified representation of a User object.
 */
public class User {

    @SerializedName("businessPhones")
    private List<String> mBusinessPhones;

    @SerializedName("displayName")
    private String mDisplayName;

    @SerializedName("givenName")
    private String mGivenName;

    @SerializedName("jobTitle")
    private String mJobTitle;

    @SerializedName("mail")
    private String mMail;

    @SerializedName("mobilePhone")
    private String mMobilePhone;

    @SerializedName("officeLocation")
    private String mOfficeLocation;

    @SerializedName("preferredLanguage")
    private String mPreferredLanguage;

    @SerializedName("surname")
    private String mSurname;

    @SerializedName("userPrincipalName")
    private String mUserPrincipalName;

    @SerializedName("id")
    private String mId;

    public List<String> getBusinessPhones() {
        return mBusinessPhones;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public String getGivenName() {
        return mGivenName;
    }

    public String getJobTitle() {
        return mJobTitle;
    }

    public String getMail() {
        return mMail;
    }

    public String getMobilePhone() {
        return mMobilePhone;
    }

    public String getOfficeLocation() {
        return mOfficeLocation;
    }

    public String getPreferredLanguage() {
        return mPreferredLanguage;
    }

    public String getSurname() {
        return mSurname;
    }

    public String getUserPrincipalName() {
        return mUserPrincipalName;
    }

    public String getId() {
        return mId;
    }
}

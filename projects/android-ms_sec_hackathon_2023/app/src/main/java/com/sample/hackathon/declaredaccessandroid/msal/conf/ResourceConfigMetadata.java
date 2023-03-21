package com.sample.hackathon.declaredaccessandroid.msal.conf;

import java.util.HashMap;
import java.util.Map;

public class ResourceConfigMetadata {

    // Key is hostname
    // Value is the config
    public static final Map<String, ResourceConfiguration> RESOURCE_CONFIGURATION_MAP = new HashMap<>();

    static {
        RESOURCE_CONFIGURATION_MAP.put(
                "graph.microsoft.com", // This is junky but illustrates the idea...
                new ResourceConfiguration(
                        "https://graph.microsoft.com",
                        "00000003-0000-0000-c000-0000000000"
                )
        );
    }

}
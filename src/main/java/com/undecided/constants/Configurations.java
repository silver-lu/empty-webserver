package com.undecided.constants;

import com.undecided.enums.HttpRequestMethod;

import java.util.*;

/**
 * Created by silver.lu on 11/17/14.
 */
public class Configurations {
    private final static Map<String, String> REDIRECTS = new HashMap<String, String>() {
        {
            put("/redirect", "http://localhost:5000/");
        }
    };

    private final static Map<String, List<HttpRequestMethod>> RESTRICTED_METHODS = new HashMap<String, List<HttpRequestMethod>>() {
        {
            put("/file1", Arrays.asList(HttpRequestMethod.Put, HttpRequestMethod.Post));
            put("/text-file.txt", Arrays.asList(HttpRequestMethod.Post));
        }
    };

    public Map<String, String> getRedirectsConfig() {
        return REDIRECTS;
    }

    public Map<String, List<HttpRequestMethod>> getRestrictedMethodsConfig() {
        return RESTRICTED_METHODS;
    }
}

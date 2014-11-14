package com.undecided.constants;

/**
 * Created by silver.lu on 11/11/14.
 */
public interface HttpResponseConstant {
    public static final String TPL_SERVER_TYPE = "Server: %s%n";
    public static final String TPL_CONTENT_TYPE = "Content-Type: %s; charset=%s%n";
    public static final String TPL_CONTENT_LENGTH = "Content-Length: %d%n";
    public static final String TPL_RESPONSE_CODE = "%s %s%n" ;
    public static final String TPL_RESPONSE_TIMESTAMP = "Date: %s%n";
    public static final String TPL_ALLOWED_METHODS = "Allow: %s%n";
    public static final String TPL_BASIC_AUTH = "WWW-Authenticate: Basic realm=\"%s\"%n";
    public static final String TPL_MIME_TYPE = "Content-Type: %s%n";
    public static final String TPL_NEW_LOCATION = "Location: %s%n";
}

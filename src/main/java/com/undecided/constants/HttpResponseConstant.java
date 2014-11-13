package com.undecided.constants;

/**
 * Created by silver.lu on 11/11/14.
 */
public interface HttpResponseConstant {
    public static final String TPL_SERVER_TYPE = "Server: %s\r\n";
    public static final String TPL_CONTENT_TYPE = "Content-Type: %s; charset=%s\r\n";
    public static final String TPL_CONTENT_LENGTH = "Content-Length: %d\r\n";
    public static final String TPL_RESPONSE_CODE = "%s %s\r\n" ;
    public static final String TPL_RESPONSE_TIMESTAMP = "Date: %s\r\n";
    public static final String TPL_ALLOWED_METHODS = "Allow: %s\r\n";
}

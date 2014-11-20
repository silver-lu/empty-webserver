package com.undecided.requests;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by silver.lu on 11/18/14.
 */
public class RequestBody {
    private String rawInput;
    Map<String, String> params;

    public RequestBody(String rawInput) {
        this.rawInput = rawInput;
        params = new HashMap<String, String>();
    }

    public void parse() {
        if (rawInput.length() > 0 && rawInput.indexOf("=") != -1) {
            String[] params = rawInput.split("&");
            for (String param : params) {
                String name = param.split("=")[0];
                String value = param.split("=")[1];
                this.params.put(name, value);
            }
        }
    }

    public String getParam(String paramName) {
        return params.get(paramName);
    }

    public byte[] getContent() {
        return rawInput.getBytes();
    }
}

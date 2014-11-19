package com.undecided.utils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class UrlQueryStringDecode {
    private URL url;
    private String urlString;
    private String[] split;
    private String decodedString;
    private Map<String,String> valuePair = new HashMap<String, String>();

    public UrlQueryStringDecode(URL url){
        this.url = url;
    }
    public UrlQueryStringDecode(String urlString){
        this.urlString = urlString;
    }

    public String getParamQuery() {
        return url.getQuery();
    }


    public String[] getSplitURLContent(String urlString) {
        return this.split = urlString.split("&");
    }

    public Map<String, String> ParamsPair(){
        //for (String elm : )
    }

    public String decodeQueryString(String urlString) throws UnsupportedEncodingException {
        return decodedString = URLDecoder.decode(urlString, "UTF-8");
    }

    public String getDecodedString() {
        return decodedString;
    }

    public String[] getSplit() {
        return split;
    }

    public String getUrlString() {
        return urlString;
    }

    public Map<String, String> getValuePair() {
        return valuePair;
    }
}

package com.undecided.utils;

import com.undecided.requests.Request;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class UrlQueryStringDecodeTest {
    @Test
    public void testIfReturnQuery() throws MalformedURLException {
        URL url = new URL("http://localhost/?test");

        UrlQueryStringDecode p = new UrlQueryStringDecode(url);
        String result = p.getParamQuery();

        assertEquals("test", result);

    }

    @Test
    public void testIfSplitURLByAmpersand() throws Exception {
        String url = "/?test=1&secondTest=2";
        UrlQueryStringDecode p = new UrlQueryStringDecode(url);
        String result = Arrays.toString(p.getSplitURLContent(url));

        assertEquals("[test=1, secondTest=2]", result);

    }

    @Test
    public void getDecodeURL() throws Exception{
        String url = new String("/parameters?variable_1=Operators%20%3C%2C%20%3E");
        UrlQueryStringDecode p = new UrlQueryStringDecode(url);
        String result = p.decodeQueryString(url);//URLDecoder.decode(url, "UTF-8");

        assertEquals("/parameters?variable_1=Operators <, >", result);
    }



}


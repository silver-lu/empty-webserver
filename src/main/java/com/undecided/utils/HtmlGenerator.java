package com.undecided.utils;

import java.util.ArrayList;
import java.util.List;

public class HtmlGenerator {

    private String bodyContent;
    private List<String> DirFiles;
    private List<String> strHolder;

    public HtmlGenerator(List<String> DirFiles) {
        this.DirFiles = DirFiles;
    }

    String beginBodyContent = "<!doctype html><html><body>";
    String endBodyContent = "</body></html>";

    public List<String> getHtmlLinks() {
        String links = "";
        strHolder = new ArrayList<String>();

        for (String file : DirFiles) {
            links = "<a href='/" + file + "'>" + file + "</a>";

            strHolder.add(links);
        }
        return strHolder;
    }

    public String getStringLinks() {

        String links = "";

        for (String link : getHtmlLinks()) {
            links += link;
        }
        return links;
    }

    public String getBody() {
        String body = "";

        body = beginBodyContent;
        body += getStringLinks();
        body += endBodyContent;

        return body;
    }



}

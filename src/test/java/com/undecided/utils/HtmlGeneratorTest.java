package com.undecided.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HtmlGeneratorTest {

    @Test
    public void testShouldMakeLinks(){
        List<String> files = new ArrayList<String>(
                Arrays.asList( "Movie","Application", "doc", "movie" )
        );
        HtmlGenerator dirString = new HtmlGenerator(files);

        List<String> dirLinks = dirString.getHtmlLinks();

        String file = files.get(0);
        String link = dirLinks.get(0);

        assertTrue(link.contains(file));
    }

}

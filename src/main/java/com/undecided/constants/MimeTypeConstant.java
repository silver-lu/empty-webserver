package com.undecided.constants;

import com.undecided.enums.FileExtension;
import com.undecided.enums.HttpResponseCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by silver.lu on 11/13/14.
 */
public interface MimeTypeConstant {

    public final static String FILE_TYPE_TEXT = "text/plain";
    public final static String FILE_TYPE_JPEG = "image/jpeg";
    public final static String FILE_TYPE_GIF = "image/gif";
    public final static String FILE_TYPE_PNG = "image/png";
    public final static String FILE_TYPE_HTML = "text/html";
    public final static String FILE_TYPE_XML = "text/xml";

    public final static Map<FileExtension, String> MIME_TYPE = new HashMap<FileExtension, String>() {
        {
            put(FileExtension.Txt, FILE_TYPE_TEXT);
            put(FileExtension.Jpeg, FILE_TYPE_JPEG);
            put(FileExtension.Gif, FILE_TYPE_GIF);
            put(FileExtension.Png, FILE_TYPE_PNG);
            put(FileExtension.Html, FILE_TYPE_HTML);
            put(FileExtension.Xml, FILE_TYPE_XML);
        }
    };

    public final static String EXTENSION_TXT = "txt";
    public final static String EXTENSION_JPG = "jpg";
    public final static String EXTENSION_JPEG = "jpeg";
    public final static String EXTENSION_GIF = "gif";
    public final static String EXTENSION_PNG = "png";
    public final static String EXTENSION_HTML = "html";
    public final static String EXTENSION_XML = "xml";

    public final static Map<String,FileExtension> EXTENTION = new HashMap<String,FileExtension>() {
        {
            put(EXTENSION_TXT, FileExtension.Txt);
            put(EXTENSION_JPEG, FileExtension.Jpeg);
            put(EXTENSION_JPG, FileExtension.Jpeg);
            put(EXTENSION_GIF, FileExtension.Gif);
            put(EXTENSION_PNG, FileExtension.Png);
            put(EXTENSION_HTML, FileExtension.Html);
            put(EXTENSION_XML, FileExtension.Xml);
        }
    };
}

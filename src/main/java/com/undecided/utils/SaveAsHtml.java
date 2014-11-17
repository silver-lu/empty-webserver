package com.undecided.utils;

import java.io.*;

public class SaveAsHtml {

    private File filePath;
    private String content;
    private String nameOfFile;

    public SaveAsHtml(String content){
        this.content = content;
    }


    public void saveFile() {
        try{
            File file = new File("index.html");

            if (!file.exists()){
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write(content);
            System.out.println(content);
            bufferWriter.close();


        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

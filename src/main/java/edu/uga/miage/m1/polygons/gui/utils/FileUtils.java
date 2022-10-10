package edu.uga.miage.m1.polygons.gui.utils;

import java.io.File;

public class FileUtils {

    private FileUtils(){}

    public static String getExtension(File file){
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

}

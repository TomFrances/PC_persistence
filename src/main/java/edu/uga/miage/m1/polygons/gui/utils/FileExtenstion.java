package edu.uga.miage.m1.polygons.gui.utils;

import java.io.File;

public class FileExtenstion {

    private FileExtenstion() {}

    public static String get(File file){
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

}

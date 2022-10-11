package edu.uga.miage.m1.polygons.gui.utils;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileUtils {

    private FileUtils(){}

    public static String getExtension(File file){
        String fileName = file.getName();
        fileName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return fileName;
    }
    public static File chooseFile(){
        JFileChooser chooser = new JFileChooser("./");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Fichiers xml ou json uniquement", "xml", "json");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(JDrawingFrame.panel);
        return chooser.getSelectedFile();
    }
}

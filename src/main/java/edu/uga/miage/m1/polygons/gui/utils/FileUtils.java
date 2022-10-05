package edu.uga.miage.m1.polygons.gui.utils;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileUtils {

    public String getExtension(File file){
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

    public File openFile() {
        JFileChooser chooser = new JFileChooser("./");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Fichiers xml ou json uniquement", "xml", "json");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(JDrawingFrame.panel);
        return chooser.getSelectedFile();
    }
}

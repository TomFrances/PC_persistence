package edu.uga.miage.m1.polygons.gui.file_management;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.strategy.ImportFile;
import edu.uga.miage.m1.polygons.gui.strategy.JsonImportStrategy;
import edu.uga.miage.m1.polygons.gui.strategy.XMLImportStrategy;
import edu.uga.miage.m1.polygons.gui.utils.FileExtenstion;
import lombok.extern.java.Log;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

@Log
public class Import {

    private Import() {
    }

    public static List<SimpleShape> importShapesFile() {
        File file = Import.openFile();
        ImportFile importFile = new ImportFile(file);

        String fileType = FileExtenstion.get(file);
        switch (fileType) {
            case "xml":
                return importFile.createShapesList(new XMLImportStrategy());
            case "json":
                return importFile.createShapesList(new JsonImportStrategy());
            default:
                log.log(Level.WARNING, "Type de fichier non pris en charge pour l'import");
        }
        return Collections.emptyList();
    }

    private static File openFile() {
        JFileChooser chooser = new JFileChooser("./");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Fichiers xml ou json uniquement", "xml", "json");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(JDrawingFrame.panel);
        return chooser.getSelectedFile();
    }
}

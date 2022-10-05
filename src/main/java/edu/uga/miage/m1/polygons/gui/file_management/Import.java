package edu.uga.miage.m1.polygons.gui.file_management;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.strategy.ImportFile;
import edu.uga.miage.m1.polygons.gui.strategy.JsonImportStrategy;
import edu.uga.miage.m1.polygons.gui.strategy.XMLImportStrategy;
import edu.uga.miage.m1.polygons.gui.utils.FileUtils;
import lombok.Getter;
import lombok.extern.java.Log;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

@Log
@Getter
public class Import {

    private final FileUtils fileUtils = new FileUtils();

    public List<SimpleShape> importShapesFile() {
        File file = fileUtils.openFile();
        ImportFile importFile = new ImportFile(file);

        String fileType = fileUtils.getExtension(file);
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
}

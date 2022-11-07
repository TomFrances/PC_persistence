package edu.uga.miage.m1.polygons.gui;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Star;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import org.assertj.swing.core.MouseButton;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JFileChooserFixture;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class JDrawingFrameE2ETest {
    private FrameFixture window;
    private JDrawingFrame frame;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setUp() {
        frame = GuiActionRunner.execute(() -> new JDrawingFrame("UITesting"));
        window = new FrameFixture(frame);
        window.show().mp1.ximize();
    }

    @Test
    void testE2EJDrawingFrame2() throws InterruptedException {
        int x;
        int y;
        Point point;
        int xVariation = 69;
        int yVariation = 46;

        window.button("CIRCLE").click();

        x = 50;
        y = 75;
        point = new Point(x + xVariation, y + yVariation);
        window.panel("drawingPanel").robot().moveMouse(point);
        window.panel("drawingPanel").robot().pressMouse(MouseButton.LEFT_BUTTON);
        window.panel("drawingPanel").robot().releaseMouse(MouseButton.LEFT_BUTTON);

        assertEquals(1, frame.getShapesList().size());
        assertTrue(frame.getShapesList().get(0) instanceof Circle);
        assertEquals(x, frame.getShapesList().get(0).getX());
        assertEquals(y, frame.getShapesList().get(0).getY());

        window.button("TRIANGLE").click();

        x = 189;
        y = 110;
        point = new Point(x + xVariation, y + yVariation);
        window.panel("drawingPanel").robot().moveMouse(point);
        window.panel("drawingPanel").robot().pressMouse(MouseButton.LEFT_BUTTON);
        window.panel("drawingPanel").robot().releaseMouse(MouseButton.LEFT_BUTTON);

        assertEquals(2, frame.getShapesList().size());
        assertTrue(frame.getShapesList().get(1) instanceof Triangle);
        assertEquals(x, frame.getShapesList().get(1).getX());
        assertEquals(y, frame.getShapesList().get(1).getY());

        window.button("STAR").click();

        x = 469;
        y = 99;
        point = new Point(x + xVariation, y + yVariation);
        window.panel("drawingPanel").robot().moveMouse(point);
        window.panel("drawingPanel").robot().pressMouse(MouseButton.LEFT_BUTTON);
        window.panel("drawingPanel").robot().releaseMouse(MouseButton.LEFT_BUTTON);

        assertEquals(3, frame.getShapesList().size());
        assertTrue(frame.getShapesList().get(2) instanceof Star);
        assertEquals(x, frame.getShapesList().get(2).getX());
        assertEquals(y, frame.getShapesList().get(2).getY());

        window.button("SQUARE").click();

        x = 455;
        y = 234;
        point = new Point(x + xVariation, y + yVariation);
        window.panel("drawingPanel").robot().moveMouse(point);
        window.panel("drawingPanel").robot().pressMouse(MouseButton.LEFT_BUTTON);
        window.panel("drawingPanel").robot().releaseMouse(MouseButton.LEFT_BUTTON);

        assertEquals(4, frame.getShapesList().size());
        assertTrue(frame.getShapesList().get(3) instanceof Square);
        assertEquals(x, frame.getShapesList().get(3).getX());
        assertEquals(y, frame.getShapesList().get(3).getY());
    }

    @Test
    void testE2EJDrawingFrameSuccess(){
        window.menuItemWithPath("File", "Import").click();
        JFileChooserFixture fileChoose = window.dialog().fileChooser().setCurrentDirectory(new File("./src/test/ressources/"));
        fileChoose.fileNameTextBox().setText("jsonSuccess.json");
        fileChoose.approve();
        assertEquals(4,frame.getShapesList().size());
        assertSame(frame.getShapesList().get(0).getClass(), Star.class);
        assertSame(frame.getShapesList().get(1).getClass(), Circle.class);
        assertSame(frame.getShapesList().get(2).getClass(), Triangle.class);
        assertSame(frame.getShapesList().get(3).getClass(), Square.class);
    }
    @Test
    void testE2EJDrawingFrameFail() {
        window.menuItemWithPath("File", "Import").click();
        JFileChooserFixture fileChoose = window.dialog().fileChooser().setCurrentDirectory(new File("./src/test/ressources/"));
        fileChoose.fileNameTextBox().setText("jsonFail.json");
        fileChoose.approve();
        assertEquals(0,frame.getShapesList().size());
    }

    @Test
    void testE2EJDrawingFrameExport() throws IOException {
        int x = 50;
        int y = 223;
        Point point;
        int xVariation = 69;
        int yVariation = 46;

        point = new Point(x + xVariation, y + yVariation);
        window.panel("drawingPanel").robot().moveMouse(point);
        window.panel("drawingPanel").robot().pressMouse(MouseButton.LEFT_BUTTON);
        window.panel("drawingPanel").robot().releaseMouse(MouseButton.LEFT_BUTTON);

        window.menuItemWithPath("File", "Export","JSON").click();
        String fileContent = Files.readString(Path.of(new File("./jsonExport.json").getPath()));
        String expectedContent = "{\"shapes\": [{\"type\":\"square\",\"x\":50,\"y\":223}]}";
        assertEquals(fileContent,expectedContent);
        window.menuItemWithPath("File", "Export","XML").click();
        fileContent = Files.readString(Path.of(new File("./xmlExport.xml").getPath()));
        expectedContent = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><root><shapes><shape><type>square</type><x>50</x><y>223</y></shape></shapes></root>";
        assertEquals(fileContent,expectedContent);
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }
}
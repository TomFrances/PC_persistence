package edu.uga.miage.m1.polygons.gui;

import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JMenuItemFixture;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

class JDrawingFrameE2ETest {
    private FrameFixture window;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setUp() {
        JDrawingFrame frame = GuiActionRunner.execute(() -> new JDrawingFrame("UITesting"));
        window = new FrameFixture(frame);
        window.show();
    }

    @Test
    void testE2EJDrawingFrame() throws InterruptedException {
        window.moveTo(new Point(200, 150)).click();
        Thread.sleep(500);

        window.button("CIRCLE").click();
        Thread.sleep(500);

        window.moveTo(new Point(150, 150)).click();
        Thread.sleep(500);

        window.button("TRIANGLE").click();
        Thread.sleep(500);

        window.moveTo(new Point(250, 250)).click();
        Thread.sleep(500);

        window.button("STAR").click();

        window.moveTo(new Point(150, 200)).click();
        Thread.sleep(500);

        window.menuItemWithPath("File", "Export", "JSON").click();
        Thread.sleep(2000);
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }
}
package edu.uga.miage.m1.polygons.gui;

import lombok.extern.java.Log;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import javp1.x.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class JDrawingFrameTest {
    @Test
    void testCreateToolsMenu() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        JDrawingFrame jDrawingFrame = new JDrawingFrame("test");
        Method createToolsMenuMethod = JDrawingFrame.class.getDeclaredMethod("createToolsMenu");
        createToolsMenuMethod.setAccessible(true);
        JMenuBar menuBar = (JMenuBar) createToolsMenuMethod.invoke(jDrawingFrame);
        assertTrue(true);
        assertEquals(1, menuBar.getMenuCount());
        assertEquals("File", menuBar.getMenu(0).getText());
        assertEquals(2, menuBar.getMenu(0).getItemCount());
        assertEquals("Export", menuBar.getMenu(0).getItem(0).getText());
        assertEquals("Import", menuBar.getMenu(0).getItem(1).getText());
    }


}
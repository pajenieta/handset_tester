package org.edc.ht.screens;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

public abstract class AbstractCanvasScreen extends Canvas {

    protected final MIDlet midlet;
    protected final Displayable mainMenu;

    protected int x;
    protected int y;

    protected AbstractCanvasScreen(MIDlet midlet, Displayable mainMenu) {
        this.midlet = midlet;
        this.mainMenu = mainMenu;
        setFullScreenMode(true);
    }

    protected void clearScreen(Graphics g) {
        // fill the entire screen with gray
        g.setColor(0xDDDDDD);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    protected void showNotify() {
        super.showNotify();
        setFullScreenMode(true);
    }

    protected void keyPressed(int keyCode) {
        if (keyCode == KEY_NUM0)
            mainMenu();
    }

    protected void mainMenu() {
        Display.getDisplay(midlet).setCurrent(mainMenu);
    }

    protected void println(String text, Graphics g, Font font) {
        g.setFont(font);
        g.drawString(text, x, y, Graphics.BASELINE | Graphics.LEFT);
        y += (font.getHeight() * 1.2);
    }

    protected Font getFontSize(int size) {
        return Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, size);
    }

    protected int getHeightPercent(int percent) {
        return (int) (((float) percent / 100f) * getHeight());
    }

    protected int getWidthPercent(int percent) {
        return (int) (((float) percent / 100f) * getWidth());
    }

}

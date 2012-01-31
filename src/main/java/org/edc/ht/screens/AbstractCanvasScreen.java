package org.edc.ht.screens;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

import org.edc.j2me.util.FontUtil;
import org.edc.j2me.widget.ToolBarButton;

public abstract class AbstractCanvasScreen extends Canvas {

    protected static final int[] FONT_SIZES = { Font.SIZE_SMALL, Font.SIZE_MEDIUM, Font.SIZE_LARGE };

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

    protected static final int getFontSizeIdx(Font font) {
        return getFontSizeIdx(font.getSize());
    }

    protected static final int getFontSizeIdx(int fontSize) {
        for (int i = 0; i < FONT_SIZES.length; i++) {
            if (FONT_SIZES[i] == fontSize)
                return i;
        }
        return 1;
    }

    protected void useFontColor(Graphics g) {
        g.setColor(0xFF0000);
    }

    protected void renderToolBar(Graphics g, ToolBarButton leftButton, ToolBarButton centerButton,
            ToolBarButton rightButton) {

        int oldColor = g.getColor();

        int screenWidth = getWidth();
        int screenHeight = getHeight();

        // for now, lets say we're using medium-size font to set the toolbar sz
        int toolbarHeight = ToolBarButton.getToolbarHeight(FontUtil.HEIGHT_MEDIUM);
        int toolbarPaddingTop = ToolBarButton.getToolBarPaddingTop(FontUtil.HEIGHT_MEDIUM);
        int y = getHeight() - toolbarHeight + toolbarPaddingTop;

        // draw toolbar background
        g.setColor(0xCCCCCC);
        g.fillRect(0, screenHeight - toolbarHeight, screenWidth, toolbarHeight);

        // draw toolbar top accent line
        g.setColor(0x999999);
        g.drawLine(0, screenHeight - toolbarHeight - 1, screenWidth, screenHeight - toolbarHeight - 1);

        // set color to the toolbar icon color:
        // TODO: this should be handled in ToolbarButton itself so the buttons can be diff colors.
        g.setColor(0x448544);

        if (leftButton != null) {
            int x = (int) (getWidth() * 0.1);
            leftButton.paint(g, x, y);
        }

        if (centerButton != null) {
            int x = (int) (getWidth() / 2);
            centerButton.paint(g, x, y);
        }

        if (rightButton != null) {
            int x = ((int) (getWidth() * 0.9)) - rightButton.getWidth();
            rightButton.paint(g, x, y);
        }

        g.setColor(oldColor);
    }

    public int getGameAction(int keyCode) {
        if (keyCode == -6)
            return LEFT;
        if (keyCode == -7)
            return RIGHT;
        return super.getGameAction(keyCode);
    }

    protected void printMenuIndicator(Graphics g, Font font) {
        useFontColor(g);
        y = (int) (getHeight() * 0.08);
        x = (int) (getWidth() * 0.05);
        println("0 = menu", g, font);
        y += (int) (font.getHeight() * 1.2);
    }

}

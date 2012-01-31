package org.edc.j2me.widget;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.edc.j2me.util.FontUtil;

public abstract class ToolBarButton {

    protected static final int HEIGHT = FontUtil.HEIGHT_MEDIUM;
    protected static final int WIDTH = FontUtil.W_WIDTH_MEDIUM;

    public static int getToolbarHeight(int fontHeight) {
        int height = (int) (fontHeight * 1.4);
        while ((height - fontHeight) % 2 == 1)
            height++;
        return height;
    }

    public static int getToolBarPaddingTop(int fontHeight) {
        return (getToolbarHeight(fontHeight) - fontHeight) / 2;
    }

    public abstract void paint(Graphics g, int x, int y);
    
    public int getWidth() {
        return WIDTH;
    }

}

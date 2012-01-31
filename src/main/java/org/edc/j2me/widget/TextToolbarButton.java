package org.edc.j2me.widget;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class TextToolbarButton extends ToolBarButton {

    private static final Font TOOLBAR_FONT = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);

    private final int hAlign;
    private final String text;

    public TextToolbarButton(String text, int hAlign) {
        this.hAlign = hAlign;
        this.text = text;
    }

    public void paint(Graphics g, int x, int y) {
        int oldColor = g.getColor();
        Font oldFont = g.getFont();

        g.setColor(0x448544);
        g.setFont(TOOLBAR_FONT);

        g.drawString(text, x, y, Graphics.TOP | hAlign);

        g.setFont(oldFont);
        g.setColor(oldColor);
    }
}

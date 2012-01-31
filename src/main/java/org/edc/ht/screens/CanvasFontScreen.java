package org.edc.ht.screens;

import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

public class CanvasFontScreen extends AbstractCanvasScreen {

    public CanvasFontScreen(MIDlet midlet, Displayable mainMenu) {
        super(midlet, mainMenu);
        setTitle("Canvas Fonts");
    }

    protected void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        clearScreen(g);

        Font font = getFontSize(Font.SIZE_LARGE);

        // change the color for the font
        g.setColor(0xFF0000);
        g.setFont(font);

        x = (int) (width * 0.05);
        y = height / 10;

        println("Press 0 for Main Menu", g, font);
        println("Screen: " + width + "x" + height, g, font);

        println("Fonts:", g, font);
        int[] fontSizes = { Font.SIZE_LARGE, Font.SIZE_MEDIUM, Font.SIZE_SMALL };
        for (int i = 0; i < fontSizes.length; i++) {
            font = getFontSize(fontSizes[i]);
            reportFontHeight(g, font);
            reportCharFit(g, font, width);
        }

    }

    /*
     * On many variable width fonts that use a latin alphabet, "W" is the widest letter. In my
     * tests, "X" has the same width in both the small and medium font sizes on a nokia C1-01, but W
     * is progressively wider as the font size increases. Therefore, I use "W" to measure maximum
     * possible font character width and the number of chars that can fit on a single line on a
     * Canvas.
     */
    private void reportCharFit(Graphics g, Font font, int width) {
        int maxCharsPerLine = (int) ((float) width / (float) font.stringWidth("W"));
        println(fontSizeLabel(font) + " W-chars: " + maxCharsPerLine, g, font);
    }

    private void reportFontHeight(Graphics g, Font font) {
        println(fontSizeLabel(font) + " height: " + font.getHeight(), g, font);
    }

    String fontSizeLabel(Font font) {
        String ret = "";
        switch (font.getSize()) {
            case Font.SIZE_LARGE:
                ret = "LG";
                break;
            case Font.SIZE_MEDIUM:
                ret = "MED";
                break;
            case Font.SIZE_SMALL:
                ret = "SM";
                break;
        }
        return ret;
    }

}

package org.edc.ht.screens;

import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

import org.edc.j2me.widget.TextArea;

public class TextAreaTestScreen extends AbstractCanvasScreen {

    // private Font font;
    private TextArea textArea;

    public TextAreaTestScreen(MIDlet midlet, Displayable mainMenu, String text, Font font) {
        super(midlet, mainMenu);
        setTitle("Text Area");
        textArea = new TextArea(text, getWidthPercent(80), font, 0xAA0000, 0xDDDDDD, this);
    }

    protected void paint(Graphics g) {
        clearScreen(g);
        textArea.render(g, getWidthPercent(5), getHeightPercent(8));
    }
    
    public void showNotify() {
        super.showNotify();
        repaint();
    }

}

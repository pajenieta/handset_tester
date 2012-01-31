package org.edc.ht.screens;

import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

import org.edc.j2me.widget.ArrowButton;
import org.edc.j2me.widget.TextArea;

public class TextAreaTestScreen extends AbstractFontResizableCanvas {

    private TextArea textArea;
    protected String text;

    public TextAreaTestScreen(MIDlet midlet, Displayable mainMenu, String text, Font font) {
        super(midlet, mainMenu, font);
        setTitle("Text Area");
        this.text = text;
        updateSlideContents();
    }

    protected void updateSlideContents() {
        textArea = new TextArea(text, getWidthPercent(80),
                getUpdatedFont(),
                0xAA0000, 0xDDDDDD, this);
        repaint();
    }

    protected void paint(Graphics g) {
        clearScreen(g);
        printMenuIndicator(g, getUpdatedFont());
        textArea.render(g, getWidthPercent(5), y);

        renderToolBar(g, ArrowButton.LEFT, CENTER_BUTTON, ArrowButton.RIGHT);
    }

    public void showNotify() {
        super.showNotify();
        repaint();
    }

}

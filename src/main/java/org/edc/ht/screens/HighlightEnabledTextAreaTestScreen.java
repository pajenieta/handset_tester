package org.edc.ht.screens;

import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

import org.edc.j2me.widget.HighlightEnabledTextArea;

public class HighlightEnabledTextAreaTestScreen extends AbstractCanvasScreen {

    // private Font font;
    private HighlightEnabledTextArea textArea;
    private Timer timer;
    private int highlightedTokenIdx = 0;

    public HighlightEnabledTextAreaTestScreen(MIDlet midlet, Displayable mainMenu, String text, Font font) {
        super(midlet, mainMenu);
        // We cannot use class.getName() b/c proguard obfuscates the name
        setTitle("Text Highlighting");
        textArea = new HighlightEnabledTextArea(text, getWidthPercent(80), font, 0xAA0000, 0xDDDDDD, this);
    }

    protected void paint(Graphics g) {
        clearScreen(g);
        textArea.setHighlightedToken(highlightedTokenIdx++);
        textArea.render(g, getWidthPercent(5), getHeightPercent(8));
    }

    protected void showNotify() {
        super.showNotify();
        final int tokenCount = textArea.tokenCount();

        timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                if (highlightedTokenIdx >= tokenCount) {
                    cancel();
                } else {
                    repaint();
                }
            }
        };
        timer.schedule(task, 200l, 500l);
    }

    protected void hideNotify() {
        super.hideNotify();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        highlightedTokenIdx = 0;
    }
    
}

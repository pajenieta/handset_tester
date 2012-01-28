package org.edc.ht.screens;

import java.util.Hashtable;

import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

public class CanvasScreen extends AbstractCanvasScreen {

    private static final Hashtable GAME_ACTION_NAMES = new Hashtable();
    static {
        GAME_ACTION_NAMES.put(new Integer(UP), "UP");
        GAME_ACTION_NAMES.put(new Integer(RIGHT), "RIGHT");
        GAME_ACTION_NAMES.put(new Integer(DOWN), "DOWN");
        GAME_ACTION_NAMES.put(new Integer(LEFT), "LEFT");
        GAME_ACTION_NAMES.put(new Integer(FIRE), "FIRE");
    }

    private String actionName = "";
    private String keyCodeStr = "";

    private Font font;

    // private final byte mode;

    public CanvasScreen(MIDlet midlet, Displayable mainMenu) {
        super(midlet, mainMenu);
        font = Font.getFont(Font.FACE_PROPORTIONAL,
                Font.STYLE_PLAIN,
                Font.SIZE_LARGE);
        setTitle("Canvas Keys");
    }

    protected void showNotify() {
        super.showNotify();
        setFullScreenMode(true);
    }

    protected void paint(Graphics g) {
        clearScreen(g);

        // change the color for the font
        g.setColor(0xFF0000);
        g.setFont(font);

        int width = getWidth();
        int height = getHeight();

        x = (int) (width * 0.06);
        y = height / 10;

        println("Press 0 for Main Menu", g, font);
        println("width: " + width, g, font);
        println("height: " + height, g, font);

        y += (font.getHeight() * 2);

        println("keyCode: " + keyCodeStr, g, font);
        println("action: " + actionName, g, font);
    }

    /*
     * NOTE: there are no constants for soft_left/soft_right. We need to construct lookup tables
     * that are parameterized by groups of devices.
     */
    protected void keyPressed(int keyCode) {
        super.keyPressed(keyCode);
        keyCodeStr = Integer.toString(keyCode);
        actionName = getActionName(keyCode);
        repaint();
    }

    private String getActionName(int keyCode) {
        Object v = GAME_ACTION_NAMES.get(new Integer(getGameAction(keyCode)));

        // TODO: refactor to lookup table
        if (v == null) {
            switch (keyCode) {
                case -6:
                    v = "SOFT_LEFT";
                    break;
                case -7:
                    v = "SOFT_RIGHT";
                    break;
            }
        }
        return v == null ? "[null]" : v.toString();
    }

    static interface Action {

        byte NONE = 0;

        byte PREV = 1;
        byte NEXT = 2;
        byte SELECT = 3;
    }
}

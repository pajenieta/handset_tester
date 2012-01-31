package org.edc.j2me.widget;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class ArrowButton extends ToolBarButton {

    public static final ArrowButton LEFT = new ArrowButton(Canvas.LEFT);
    public static final ArrowButton RIGHT = new ArrowButton(Canvas.RIGHT);

    // x and y offsets
    private int ax, bx, cx;
    private int ay, by, cy;

    private ArrowButton(int direction) {
        switch (direction) {
            case Canvas.LEFT:
                ax = WIDTH;
                bx = 0;
                cx = WIDTH;

                ay = 0;
                by = (HEIGHT / 2);
                cy = HEIGHT;
                break;

            case Canvas.RIGHT:
                ax = 0;
                bx = WIDTH;
                cx = 0;

                ay = 0;
                by = (HEIGHT / 2);
                cy = HEIGHT;
                break;
        }
    }

    public void paint(Graphics g, int x, int y) {
        g.fillTriangle(x + ax, y + ay, x + bx, y + by, x + cx, y + cy);
    }
}

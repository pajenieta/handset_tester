package org.edc.j2me.widget;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public abstract class Widget {

    protected Canvas canvas;

    protected Widget(Canvas canvas) {
        this.canvas = canvas;
    }

    public abstract void render(Graphics g, int x, int y);
}

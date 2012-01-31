package org.edc.ht.screens;

import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

import org.edc.j2me.widget.TextToolbarButton;
import org.edc.j2me.widget.ToolBarButton;

public abstract class AbstractFontResizableCanvas extends AbstractCanvasScreen {

    protected int fontSizeIdx;

    protected int fontFace;
    protected int fontStyle;
    
    protected static final ToolBarButton CENTER_BUTTON = new TextToolbarButton("size", Graphics.HCENTER);

    protected AbstractFontResizableCanvas(MIDlet midlet, Displayable mainMenu, Font font) {
        super(midlet, mainMenu);
        this.fontFace = font.getFace();
        this.fontStyle = font.getStyle();
        this.fontSizeIdx = getFontSizeIdx(font);
    }

    protected abstract void updateSlideContents();

    protected Font getUpdatedFont() {
        return Font.getFont(fontFace, fontStyle, FONT_SIZES[fontSizeIdx]);
    }

    public int getGameAction(int keyCode) {
        if (keyCode == -6)
            return LEFT;
        if (keyCode == -7)
            return RIGHT;
        return super.getGameAction(keyCode);
    }

    protected void keyPressed(int keyCode) {
        super.keyPressed(keyCode);
        int action = getGameAction(keyCode);
        switch (action) {
            case LEFT:
                if (decFontSize()) {
                    updateSlideContents();
                }
                break;
            case RIGHT:
                if (incFontSize()) {
                    updateSlideContents();
                }
                break;
        }
    }

    private boolean decFontSize() {
        if (fontSizeIdx > 0) {
            fontSizeIdx--;
            return true;
        }
        return false;
    }

    private boolean incFontSize() {
        if (fontSizeIdx < 2) {
            fontSizeIdx++;
            return true;
        }
        return false;
    }

}

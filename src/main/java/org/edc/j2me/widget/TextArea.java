package org.edc.j2me.widget;

import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.edc.util.StringTokenizer;

/**
 * TODO: extend some base Widget class that provides methods for controlling geometry.
 * 
 * @author Greg Orlowski
 */
public class TextArea extends Widget {

    /**
     * line spacing in em (this should be configurable)
     */
    protected float lineSpacing = 0.2f;

    protected final int width;
    protected boolean enableHighlighting = false;

    private final String text;
    protected Vector lines = new Vector(); // vector of strings

    protected final Font font;
    private final int fontColor;
    protected final int bgColor;

    public TextArea(String text, int width, Font font, int fontColor, int bgColor, Canvas canvas) {
        super(canvas);
        this.text = text;
        this.font = font;
        this.fontColor = fontColor;
        this.bgColor = bgColor;
        this.width = width;
        splitLines();
    }

    protected void prerender(Graphics g, int x, int y) {
        g.setFont(font);
        g.setColor(fontColor);
    }

    public void render(Graphics g, int x, int y) {
        prerender(g, x, y);
        for (int i = 0; i < lines.size(); i++) {
            String line = (String) lines.elementAt(i);
            g.drawString(line, x, y, Graphics.TOP | Graphics.LEFT);
            y += (int) (font.getHeight() * (float) (1 + lineSpacing));
        }
    }

    protected void splitLines() {
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(text, " ");

        int spaceWidth = font.stringWidth(" ");
        int tokenWidth = 0;

        for (String token = null; st.hasMoreTokens();) {
            token = st.nextToken();
            if (token != null) {
                tokenWidth = font.stringWidth(token);

                // If the token itself exceeds the width then just overrun the
                // boundaries (maybe I need to clip when rendering?)
                if (tokenWidth >= width) {
                    if (sb.length() >= 0)
                        lines.addElement(sb.toString());
                    lines.addElement(token);
                    appendTokenCoordinates(token, tokenWidth);
                } else if (strWidth(sb, spaceWidth, tokenWidth) >= width) {
                    lines.addElement(sb.toString());
                    sb.delete(0, sb.length());
                    appendToken(sb, token, tokenWidth);
                } else {
                    appendToken(sb, token, tokenWidth);
                }
            }
        }

        // append leftovers
        if (sb.length() > 0) {
            lines.addElement(sb.toString());
        }
    }
    
    protected void appendToken(StringBuffer sb, String token, int tokenWidth) {
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) != '-')
            sb.append(' ');
        appendTokenCoordinates(sb, token, tokenWidth);
        sb.append(token);
    }

    protected void appendTokenCoordinates(String token, int tokenWidth) {
        // do nothing
    }

    protected void appendTokenCoordinates(StringBuffer sb, String token, int tokenWidth) {
        // do nothing
    }

    int strWidth(StringBuffer sb, int spaceWidth, int tokenWidth) {
        int width = font.stringWidth(sb.toString()) + tokenWidth;
        return (sb.length() > 0 && sb.charAt(sb.length() - 1) == '-')
                ? width
                : width + spaceWidth;
    }

    /*
     * Accessors
     */
    public void setLineSpacing(float lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    /*
     * TODO: move all this margin stuff into a base class
     */
    // public void setWidthPercent(int percent) {
    // widthPixels = (int) (((float) percent / 100f) * canvas.getWidth());
    // }
    //
    // public void setWidth(int widthInPixels) {
    // widthPixels = widthInPixels;
    // }

    // public void setMarginLeftPercent(int percent) {
    // marginLeftPixels = (int) (((float) percent / 100f) * canvas.getWidth());
    // }
    //
    // public void setMarginLeft(int pixels) {
    // marginLeftPixels = pixels;
    // }
    //
    // public void setMarginTop(int pixels) {
    // marginTopPixels = pixels;
    // }
    //
    // public void setMarginTopPercent(int percent) {
    // marginTopPixels = (int) (((float) percent / 100f) * canvas.getHeight());
    // }

    protected int getLineCount() {
        return lines.size();
    }

    protected String getLine(int idx) {
        return (String) lines.elementAt(idx);
    }
}

package org.edc.j2me.widget;

import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class HighlightEnabledTextArea extends TextArea {

    protected Vector tokenCoordinates;
    private int highlightedToken;

    public HighlightEnabledTextArea(String text, int width, Font font, int fontColor, int bgColor, Canvas canvas) {
        super(text, width, font, fontColor, bgColor, canvas);
    }

    protected void splitLines() {
        tokenCoordinates = new Vector();
        super.splitLines();
    }

    public void render(Graphics g, int x, int y) {
        prerender(g, x, y);

        // TODO: I must track screen y start position + marginTopPixels
        TokenCoordinate tc = (TokenCoordinate) tokenCoordinates.elementAt(highlightedToken);

        for (int i = 0; i < getLineCount(); i++) {
            String line = getLine(i);

            if (tc.lineIdx == i) {
                int color = g.getColor();
                g.setColor(0xF6F27B);
                g.fillRect(x + font.substringWidth(line, 0, tc.startPos) - 1,
                        y,
                        tc.tokenWidth + 1,
                        font.getHeight());
                g.setColor(color);
            }

            g.drawString(line, x, y, Graphics.TOP | Graphics.LEFT);
            y += (int) (font.getHeight() * (float) (1 + lineSpacing));
        }
    }

    /*
     * TODO: support highlighting > 1 token at a time?
     */

    public int tokenCount() {
        return tokenCoordinates.size();
    }

    protected void appendTokenCoordinates(String token, int tokenWidth) {
        tokenCoordinates.addElement(new TokenCoordinate(lines.size() - 1, 0, token.length(), tokenWidth));
    }

    protected void appendTokenCoordinates(StringBuffer sb, String token, int tokenWidth) {
        int startIdx = sb.length();
        tokenCoordinates.addElement(new TokenCoordinate(lines.size(), startIdx, startIdx + token.length(), tokenWidth));
    }

    // public int getHighlightedToken() {
    // return highlightedToken;
    // }

    public void setHighlightedToken(int highlightedToken) {
        this.highlightedToken = highlightedToken;
    }

    static class TokenCoordinate {
        final int lineIdx;
        final int startPos;
        final int endPos;
        final int tokenWidth;

        TokenCoordinate(int lineIdx, int startPos, int endPos, int tokenWidth) {
            this.lineIdx = lineIdx;
            this.startPos = startPos;
            this.endPos = endPos;
            this.tokenWidth = tokenWidth;
        }
    }

    protected void prerender(Graphics g, int x, int y) {
        g.setColor(bgColor);
        int rectHeight = (lines.size() == 0)
                ? 0
                : font.getHeight() + (lines.size() - 1 * (int) (font.getHeight() * (float) (1 + lineSpacing)));

        g.fillRect(x, y, width, rectHeight);
        super.prerender(g, x, y);
    }

}

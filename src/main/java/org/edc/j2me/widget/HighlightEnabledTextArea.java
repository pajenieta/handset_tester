package org.edc.j2me.widget;

import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class HighlightEnabledTextArea extends TextArea {

    protected Vector tokens;
    private int highlightedTokenIdx;

    public HighlightEnabledTextArea(String text, int width, Font font, int fontColor, int bgColor, Canvas canvas) {
        super(text, width, font, fontColor, bgColor, canvas);
    }

    protected void splitLines() {
        tokens = new Vector();
        super.splitLines();
    }

    public void render(Graphics g, int x, int y) {
        prerender(g, x, y);

        // TODO: I must track screen y start position + marginTopPixels
        Token tc = (Token) tokens.elementAt(highlightedTokenIdx);

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
        return tokens.size();
    }

    protected void appendTokenCoordinates(String token, int tokenWidth) {
        tokens.addElement(new Token(token, lines.size() - 1, 0, token.length(), tokenWidth));
    }

    protected void appendTokenCoordinates(StringBuffer sb, String token, int tokenWidth) {
        int startIdx = sb.length();
        tokens.addElement(new Token(token, lines.size(), startIdx, startIdx + token.length(), tokenWidth));
    }

    // public int getHighlightedToken() {
    // return highlightedToken;
    // }

    public void setHighlightedToken(int highlightedToken) {
        this.highlightedTokenIdx = highlightedToken;
    }

    static class Token {
        final int lineIdx;
        final int startPos;
        final int endPos;
        final int tokenWidth;
        final String text;

        Token(String text, int lineIdx, int startPos, int endPos, int tokenWidth) {
            this.text = text;
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

    public String getTokenText(int idx) {
        return ((Token) tokens.elementAt(idx)).text;
    }

}

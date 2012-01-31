package org.edc.j2me.util;

import javax.microedition.lcdui.Font;

public class FontUtil {

    public static final int HEIGHT_SMALL;
    public static final int HEIGHT_MEDIUM;
    public static final int HEIGHT_LARGE;

    public static final int W_WIDTH_SMALL;
    public static final int W_WIDTH_MEDIUM;
    public static final int W_WIDTH_LARGE;

    static {
        Font small = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        Font med = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
        Font large = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_LARGE);

        HEIGHT_SMALL = small.getHeight();
        HEIGHT_MEDIUM = med.getHeight();
        HEIGHT_LARGE = large.getHeight();

        W_WIDTH_SMALL = small.stringWidth("W");
        W_WIDTH_MEDIUM = med.stringWidth("W");
        W_WIDTH_LARGE = large.stringWidth("W");

    }

}

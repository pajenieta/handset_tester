package org.edc.util;

public class StringTokenizer {

    private char[] notDroppableDelims = { '-' };
    private char[] droppableDelims = { ' ', '\t', '\n', '\r' };

    private int pos = 0;
    private String str;

    public StringTokenizer(String str) {
        this.str = str;
    }

    public StringTokenizer(String str, String droppableDelims) {
        this.str = str;
        this.droppableDelims = droppableDelims.toCharArray();
    }

    public String nextToken() {
        while (pos < str.length() && isDelim(str.charAt(pos)))
            pos++;
        if (pos < str.length()) {
            StringBuffer sb = new StringBuffer();
            while (pos < str.length() && !isDelim(str.charAt(pos))) {
                sb.append(str.charAt(pos++));
            }
            if (pos < str.length() && isNonDroppableDelim(str.charAt(pos))) {
                sb.append(str.charAt(pos++));
            }
            return sb.toString();
        }
        return null;
    }

    public boolean hasMoreTokens() {
        if (pos < str.length()) {
            for (int i = pos; i < str.length(); i++) {
                if (!isDelim(str.charAt(i)))
                    return true;
            }
        }
        return false;
    }

    private boolean isDelim(char c) {
        return isDroppableDelim(c) || isNonDroppableDelim(c);
    }

    private boolean isDroppableDelim(char c) {
        return contains(droppableDelims, c);
    }

    private boolean isNonDroppableDelim(char c) {
        return contains(notDroppableDelims, c);
    }

    private static boolean contains(char[] chars, char c) {
        for (int i = 0; i < chars.length; i++) {
            if (c == chars[i])
                return true;
        }
        return false;
    }
}

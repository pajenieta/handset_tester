package org.edc.util;

import junit.framework.TestCase;

public class StringTokenizerTest extends TestCase {

    public void testNextToken() {
        StringTokenizer st = new StringTokenizer("A fox in the woods.");
        assertEquals("A", st.nextToken());
        assertEquals("fox", st.nextToken());
        assertEquals("in", st.nextToken());
        assertEquals("the", st.nextToken());
        assertEquals("woods.", st.nextToken());
        
        st = new StringTokenizer("To fine-tune, be careful.", " ");
        assertEquals("To", st.nextToken());
        assertEquals("fine-", st.nextToken());
        assertEquals("tune,", st.nextToken());
        assertEquals("be", st.nextToken());
        assertEquals("careful.", st.nextToken());
        
        st = new StringTokenizer("This is a test of our j2me TextArea widget, which should wrap.", " ");
        assertEquals("This", st.nextToken());
        assertEquals("is", st.nextToken());
        assertEquals("a", st.nextToken());
        assertEquals("test", st.nextToken());
        assertEquals("of", st.nextToken());
        assertEquals("our", st.nextToken());
        assertEquals("j2me", st.nextToken());
        assertEquals("TextArea", st.nextToken());
        assertEquals("widget,", st.nextToken());
        assertEquals("which", st.nextToken());
        assertEquals("should", st.nextToken());
        assertEquals("wrap.", st.nextToken());
    }
}

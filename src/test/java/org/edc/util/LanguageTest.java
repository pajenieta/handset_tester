package org.edc.util;

import junit.framework.TestCase;


public class LanguageTest extends TestCase {
    
    public void testContains() {
        Language lang = new Language("abcd");
        assertTrue(lang.contains('a'));
        assertFalse(lang.contains('ŋ'));
//        assertFalse(lang.contains('b'));
    }

}

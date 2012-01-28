package org.edc.util;

import junit.framework.TestCase;


public class LanguageTest extends TestCase {
    
    public void testContains() {
        Language english = new Language("ABCDEFGHIJKLMNOPQRSTUVWXYZ","abcdefghijklmnopqrstuvwxyz");
        assertTrue(english.contains('a'));
        assertTrue(english.contains('A'));
        assertFalse(english.contains('ŋ'));
        
        Language bambara = new Language("ABCDEÈFGHIJKLMNŊOÒPRSTUWXYZ","abcdeèfghijklmnŋoòprstuwxyz");
        assertTrue(bambara.contains('ŋ'));
        assertTrue(bambara.contains('Ŋ'));
        assertFalse(bambara.contains('q'));
        
        assertEquals('ŋ', bambara.lookup('Ŋ'));
        
    }

}

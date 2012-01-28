package org.edc.util;

import java.util.Hashtable;

public class Language {

    private Hashtable alphabet = new Hashtable();

    /**
     * @param keyCharacters
     *            Alternative characters that have the same name as the valueCharacters. These are
     *            typically all the uppercase characters in the alphabet. If an uppercase character
     *            is named differently than its lowercase counterpart, it should be included in both
     *            valueCharacters and alternateKeyCharacters.
     * 
     *            The keyCharacters and valueCharacters strings must be the same length. If you want
     *            to include a character that cannot be represented in multiple cases (upper and
     *            lower) in an alphabet then just include the same character in both strings in the
     *            same position so that it maps to itself. E.g., if you want to consider '_' as a
     *            word character in an alphabet that otherwise just contains "ABC" then specify
     * 
     *            new Language("ABC_","abc_")
     * 
     * @param valueCharacters
     *            The set of all distinctly voiced characters in a language. These are typically all
     *            the lowercase characters in the alphabet.
     * 
     */
    public Language(String keyCharacters, String valueCharacters) {
        for (int i = 0; i < valueCharacters.length(); i++) {
            Character k = new Character(keyCharacters.charAt(i));
            Character v = new Character(valueCharacters.charAt(i));
            alphabet.put(k, v);
            alphabet.put(v, v);
        }
    }

    public boolean contains(Character c) {
        return alphabet.containsKey(c);
    }

    public boolean contains(char c) {
        return contains(new Character(c));
    }

    public char lookup(char c) {
        return lookup(new Character(c));
    }

    public char lookup(Character c) {
        return ((Character) alphabet.get(c)).charValue();
    }

}

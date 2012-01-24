package org.edc.util;

public class Language {

    private Set alphabet = new HashSet();

    public Language(String alphabet) {
        for (int i = 0; i < alphabet.length(); i++) {
            Character c = new Character(alphabet.charAt(i));
            this.alphabet.add(c);
        }
    }

    public boolean contains(Character c) {
        return alphabet.contains(c);
    }

    public boolean contains(char c) {
        return this.contains(new Character(c));
    }

}

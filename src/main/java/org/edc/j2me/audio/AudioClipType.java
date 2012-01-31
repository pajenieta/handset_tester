package org.edc.j2me.audio;

public class AudioClipType {

    private final byte clipType;
    private final String subpath;

    private AudioClipType(byte clipType, String subpath) {
        this.clipType = clipType;
        this.subpath = subpath;
    }

    public static final AudioClipType LETTER = new AudioClipType((byte) 1, "letters");
    public static final AudioClipType SYLLABLE = new AudioClipType((byte) 2, "syllables");
    public static final AudioClipType WORD = new AudioClipType((byte) 3, "words");
    public static final AudioClipType PASSAGE = new AudioClipType((byte) 4, "passages");
    public static final AudioClipType EPISODE = new AudioClipType((byte) 5, "episodes");

    public String getSubpath() {
        return subpath;
    }

}

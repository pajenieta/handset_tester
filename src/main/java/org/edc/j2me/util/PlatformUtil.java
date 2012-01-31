package org.edc.j2me.util;

public class PlatformUtil {

    private static final String platform = System.getProperty("microedition.platform");

    // TODO: check this value on the samsung+nokia emulators
    private static final boolean emulator = ("SunMicrosystems_wtk".equals(platform));

    /*
     * TODO:
     * 
     * Does our execution environment support mp3 playback? For now, assume that sun wtk does not
     * and all other platforms do support mp3 playback.
     */
    public static boolean isMp3PlaybackEnabled() {
        return !emulator;
    }

}

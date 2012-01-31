package org.edc.j2me.audio;

import java.io.InputStream;

public class ClasspathResourceAudioPlayer extends AbstractAudioPlayer {

    public ClasspathResourceAudioPlayer(int volumeLevel) {
        super(volumeLevel);
    }

    protected InputStream getInputStream(AudioClipType audioClipType, String resourceName) {
        return getClass().getResourceAsStream(getResourcePath(audioClipType, resourceName));
    }

}

package org.edc.j2me.audio;

public interface AudioPlayer {

    public void playAudio(AudioClipType audioClipType, String resourceName);

    public void stop();

    public void play();

    public void cleanup();

}

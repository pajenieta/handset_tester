package org.edc.j2me.audio;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;

import org.edc.j2me.util.PlatformUtil;

public abstract class AbstractAudioPlayer implements AudioPlayer {

    private Player player;
    protected final String filetype;
    protected final String mimetype;
    private final int volumeLevel;
    private InputStream is;
    
    protected AbstractAudioPlayer(int volumeLevel) {
        if (PlatformUtil.isMp3PlaybackEnabled()) {
            mimetype = "audio/mpeg";
            filetype = "mp3";
        } else {
            mimetype = "audio/X-wav";
            filetype = "wav";
        }
        this.volumeLevel = volumeLevel;
    }

    public void playAudio(AudioClipType audioClipType, String resourceName) {
        if (player != null)
            cleanup();

        is = getInputStream(audioClipType, resourceName);
        try {
            player = Manager.createPlayer(is, mimetype);
            this.play();
        } catch (MediaException me) {
            throw new RuntimeException(me.getMessage());
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    String getResourcePath(AudioClipType audioClipType, String resource) {
        return "/audio/" + filetype + '/' + audioClipType.getSubpath() + '/' + resource + '.' + filetype;
    }

    protected abstract InputStream getInputStream(AudioClipType audioClipType, String resourceName);

    public void stop() {
        try {
            if (player.getState() == Player.STARTED)
                player.stop();
        } catch (MediaException me) {
            throw new RuntimeException(me.getMessage());
        }
    }

    public void play() {
        /*
         * TODO: error conditions for stopped, etc
         */
        try {
            if (player.getState() == Player.UNREALIZED) {
                player.realize();
                VolumeControl vc = (VolumeControl) player.getControl("VolumeControl");
                if (vc != null) {
                    vc.setLevel(volumeLevel);
                }
            }
            if (player.getState() == Player.REALIZED) {
                player.prefetch();
            }
            player.start();
        } catch (MediaException me) {
            throw new RuntimeException(me.getMessage());
        }
    }

    public void cleanup() {
        if (player != null) {
            if (player.getState() == Player.STARTED)
                stop();
            if (player.getState() != Player.CLOSED) {
                player.deallocate();
                player.close();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

}

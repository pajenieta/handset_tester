package org.edc.ht.screens;

import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

import org.edc.j2me.audio.AudioClipType;
import org.edc.j2me.audio.AudioPlayer;
import org.edc.j2me.audio.ClasspathResourceAudioPlayer;
import org.edc.j2me.widget.ArrowButton;
import org.edc.j2me.widget.TextToolbarButton;
import org.edc.j2me.widget.ToolBarButton;

public class AudioSlide extends AbstractCanvasScreen {

    private static final ToolBarButton LETTER_TOOLBAR_BUTTON = new TextToolbarButton("letter", Graphics.HCENTER);
    private final AudioPlayer audioPlayer;
    private char currLetter = 'a';
    private Font font = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_LARGE);

    public AudioSlide(MIDlet midlet, Displayable mainMenu) {
        super(midlet, mainMenu);
        setTitle("Audio Test");
        audioPlayer = new ClasspathResourceAudioPlayer(100);
    }

    protected void paint(Graphics g) {
        clearScreen(g);
        renderToolBar(g, ArrowButton.LEFT, LETTER_TOOLBAR_BUTTON, ArrowButton.RIGHT);
        printMenuIndicator(g, font);
        g.drawChar(currLetter, getWidth() / 2, getHeight() / 2, Graphics.BASELINE | Graphics.LEFT);
    }

    protected void showNotify() {
        super.showNotify();
        setFullScreenMode(true);
        playAndDisplayLetter();
    }

    private void playAndDisplayLetter() {
        repaint();
        audioPlayer.playAudio(AudioClipType.LETTER, new Character(currLetter).toString());
    }

    protected void hideNotify() {
        super.hideNotify();
        audioPlayer.cleanup();
        currLetter = 'a';
    }

    public void keyPressed(int keyCode) {
        super.keyPressed(keyCode);
        int action = getGameAction(keyCode);
        char c = currLetter;
        switch (action) {
            case LEFT:
                if (c != decLetter())
                    playAndDisplayLetter();
                break;
            case RIGHT:
                if (c != incLetter())
                    playAndDisplayLetter();
                break;
        }
    }

    private char incLetter() {
        int currLetterInt = (int) currLetter;
        if (currLetterInt < ((int) 'z'))
            this.currLetter = (char) (currLetterInt + 1);
        return currLetter;
    }

    private char decLetter() {
        int currLetterInt = (int) currLetter;
        if (currLetterInt > ((int) 'a'))
            this.currLetter = (char) (currLetterInt - 1);
        return currLetter;
    }

}

package org.edc;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import org.edc.ht.event.CommandRegistry;
import org.edc.ht.screens.CanvasFontScreen;
import org.edc.ht.screens.CanvasScreen;
import org.edc.ht.screens.FileSystemInfoScreen;
import org.edc.ht.screens.OnSetCurrentEventObserver;
import org.edc.ht.screens.PermissionInfo;
import org.edc.ht.screens.PhoneSpecsScreen;
import org.edc.ht.screens.HighlightEnabledTextAreaTestScreen;
import org.edc.ht.screens.TextAreaTestScreen;
import org.edc.util.log.Logger;

public class HandsetTester extends MIDlet implements CommandListener {

    private static final String DIALOG_TITLE_ABOUT = "About";
    private Command exitCommand;
    private Command mainMenuCommand;

    private Alert about;
    private List mainMenu;
    private Displayable[] menuItems;

    protected void startApp() throws MIDletStateChangeException {

        Logger.init(getAppProperty("LOG_LEVEL"));

        // mainMenuCommand = new Command("Menu", Command.SCREEN, 1);
        // exitCommand = new Command("Exit", Command.EXIT, 0);
        mainMenuCommand = CommandRegistry.mainMenu();
        exitCommand = CommandRegistry.exit();

        about = new Alert(DIALOG_TITLE_ABOUT,
                "Handset Tester -- a j2me MIDlet for testing handset capabilities and behavior",
                null, AlertType.INFO);
        about.setTimeout(Alert.FOREVER);
        initMainMenu();
        Display.getDisplay(this).setCurrent(mainMenu);
    }

    private Display getDisplay() {
        return Display.getDisplay(this);
    }

    private void initMainMenu() {
        // TODO: add images to menu items
        Font mediumFont = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);

        mainMenu = new List("Handset Tester Main Menu", List.IMPLICIT);
        menuItems = new Displayable[] {
                new PhoneSpecsScreen(getDisplay()),
                new PermissionInfo(this),
                new FileSystemInfoScreen(this, getDisplay()),
                new CanvasFontScreen(this, mainMenu),
                new CanvasScreen(this, mainMenu),
                
                new TextAreaTestScreen(
                        this,
                        mainMenu,
                        "This is a test of our j2me TextArea widget, which should wrap. This should support several lines"
                                + " of text and wrap properly.",
                        mediumFont),

                new HighlightEnabledTextAreaTestScreen(this, mainMenu,
                        "This is a test of our j2me TextArea widget, which should wrap.",
                        mediumFont)
        };
        for (int i = 0; i < menuItems.length; i++) {
            if (!(menuItems[i] instanceof CommandListener) && !(menuItems[i] instanceof Canvas)) {
                menuItems[i].setCommandListener(this);
            }

            if (!(menuItems[i] instanceof Canvas)) {
                menuItems[i].addCommand(exitCommand);
                menuItems[i].addCommand(mainMenuCommand);
            }
            mainMenu.append(menuItems[i].getTitle(), null);
        }

        Command showAbout = new Command(DIALOG_TITLE_ABOUT, Command.SCREEN, 1);
        mainMenu.addCommand(exitCommand);
        mainMenu.addCommand(showAbout);
        mainMenu.setCommandListener(this);
    }

    private void navigateToScreen(int selectedIdx) {
        Displayable screen = menuItems[selectedIdx];
        Display.getDisplay(this).setCurrent(screen);
        if (screen instanceof OnSetCurrentEventObserver) {
            ((OnSetCurrentEventObserver) screen).onSetCurrent();
        }
    }

    public void commandAction(Command c, Displayable d) {
        if (c == exitCommand) {
            try {
                destroyApp(true);
                notifyDestroyed();
            } catch (MIDletStateChangeException e) {
                throw new RuntimeException("Could not destroy");
            }
        } else if (c == List.SELECT_COMMAND) {
            navigateToScreen(mainMenu.getSelectedIndex());
        } else if (c == mainMenuCommand) {
            Display.getDisplay(this).setCurrent(mainMenu);
        } else if (DIALOG_TITLE_ABOUT.equals(c.getLabel())) {
            Display.getDisplay(this).setCurrent(about, mainMenu);
        } else {
            // this should be unreachable.
        }
    }

    /*
     * We do not need to do anything special on destroy or pause
     */
    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
    }

    protected void pauseApp() {
    }

}

package org.edc.ht.event;

import javax.microedition.lcdui.Command;

public class CommandRegistry {

    private static Command back;
    private static Command exit;
    private static Command mainMenu;

    public static Command back() {
        if (back == null) {
            back = new Command("Back", Command.BACK, 0);
        }
        return back;
    }

    public static Command exit() {
        if (exit == null)
            exit = new Command("Exit", Command.EXIT, 0);
        return exit;
    }

    public static Command mainMenu() {
        if (mainMenu == null)
            mainMenu = new Command("Menu", Command.SCREEN, 0);
        return mainMenu;
    }
}

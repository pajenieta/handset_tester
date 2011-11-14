package org.edc;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class HelloJ2ME extends MIDlet implements CommandListener {

    private Command exitCommand;

    protected void destroyApp(boolean unconditional)
            throws MIDletStateChangeException {
        // TODO Auto-generated method stub
    }

    protected void pauseApp() {
        // TODO Auto-generated method stub
    }

    protected void startApp() throws MIDletStateChangeException {
        exitCommand = new Command("Exit", Command.EXIT,0);
        Form f = new Form("Hello World");
        f.addCommand(exitCommand);
        f.setCommandListener(this);
        Display.getDisplay(this).setCurrent(f);
    }

    public void commandAction(Command c, Displayable d) {
        if(c==exitCommand) {
            try {
                destroyApp(true);
                notifyDestroyed();
            } catch (MIDletStateChangeException e) {
                throw new RuntimeException("Could not destroy");
            }
            
        }
    }

}

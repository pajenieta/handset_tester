package org.edc.ht.screens;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import org.edc.ht.event.CommandRegistry;
import org.edc.util.log.Logger;

public class FileSystemInfoScreen extends List implements CommandListener {

    private Displayable[] menuItems;

    /*
     * TODO: keep Display in some registry
     */
    private Display display;

    private CommandListener parentListener;

    public FileSystemInfoScreen(CommandListener parentListener, Display display) {
        super("File System Info", List.IMPLICIT);
        this.display = display;
        this.parentListener = parentListener;
        initMenuItems();
        setCommandListener(this);
        // TODO Auto-generated constructor stub
    }
    
    void setCurrent() {
        display.setCurrent(this);
    }

    private void initMenuItems() {
        menuItems = new Displayable[] {
                new FileBrowser(this,this)
        };
        for (int i = 0; i < menuItems.length; i++) {
            if (!(menuItems[i] instanceof CommandListener)) {
                menuItems[i].setCommandListener(this);
            }
            menuItems[i].addCommand(CommandRegistry.back());
            menuItems[i].addCommand(CommandRegistry.exit());
            append(menuItems[i].getTitle(), null);
        }
    }

    public void commandAction(Command c, Displayable d) {
        Logger.getLogger().log(this,Logger.DEBUG, "Called command action");
        if (c == List.SELECT_COMMAND) {
            Logger.getLogger().log(this,Logger.DEBUG, "Called select command");
            Displayable screen = menuItems[getSelectedIndex()];
            display.setCurrent(screen);
            if(screen != null && screen instanceof FileBrowser) {
                ((FileBrowser)screen).populate();
            }
        } else {
            parentListener.commandAction(c, d);
        }
    }

}

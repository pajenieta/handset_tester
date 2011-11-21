package org.edc.ht.screens;

import java.io.IOException;
import java.util.Enumeration;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.io.file.FileSystemRegistry;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import org.edc.ht.event.CommandRegistry;
import org.edc.util.log.Logger;

/*
 * TODO: put hideous URL-path string manipulation code in a separate class
 * that just controls the state of the file path (similar to java.io.File)
 */

public class FileBrowser extends List implements CommandListener {

    private CommandListener parentListener;
    private Displayable parent;
    private String url = null;
    private int depth = 0;

    public FileBrowser(Displayable parent, CommandListener parentListener) {
        super("File Browser", List.IMPLICIT);
        this.parent = parent;
        this.parentListener = parentListener;
        setCommandListener(this);
    }

    public void populate() {
        deleteAll();
        if (depth == 0) {
            // listRootsInThread();
            listRoots();
            // append("ASD",null);
        } else {
            listUrl();
        }
    }

//    private void listRootsInThread() {
//        Thread t = new Thread() {
//            public void run() {
//                listRoots();
//            }
//        };
//        t.run();
//    }

    private void listRoots() {
        Logger.getLogger().debug(this, "Preparing to list fs roots");
        for (Enumeration e = FileSystemRegistry.listRoots(); e.hasMoreElements();) {
            String root = (String) e.nextElement();
            append(root, null);
        }
    }

    private void listUrl() {
        FileConnection fc = null;
        try {
            Logger.getLogger().log(this, Logger.DEBUG, "Preparint to open url: " + url);
            fc = (FileConnection) Connector.open(url);
            if (fc.isDirectory()) {
                for (Enumeration e = fc.list(); e.hasMoreElements();) {
                    append((String) e.nextElement(), null);
                }
            } else {
                // TODO: alert that this is a file not a directory
                // TODO: I could also add file/directory icons or [F] / [D] indicators
                Logger.getLogger().log(this, Logger.DEBUG, "Cannot list because url is a file: " + url);
            }
        } catch (IOException ioe) {
            Logger.getLogger().log(this, Logger.ERROR, "Threw IOException reading: " + url);
        } finally {
            if (fc != null) {
                try {
                    fc.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    private void navigateUp() {
        Logger.getLogger().debug(this, "navigating up. url is: " + url);
        url = url.substring(0, stripTrailingSlashes(url).lastIndexOf('/') + 1);
        Logger.getLogger().debug(this, "navigating up. NEW url is: " + url);
        depth--;
        if (depth == 0)
            setRootPath();
        populate();
    }

    private String stripTrailingSlashes(String path) {
        while (path != null && path.endsWith("/"))
            path = path.substring(0, path.length() - 1);
        return path;
    }

    private String stripLeadingSlashes(String relativePath) {
        while (relativePath != null && relativePath.startsWith("/"))
            relativePath = relativePath.substring(1);
        return relativePath;
    }

    private void listDir(String relativePath) {
        Logger.getLogger().debug(this, "called listDir. url is: " + url);
        if (url == null) {
            url = "file:///" + stripLeadingSlashes(relativePath);
        } else {
            url = stripTrailingSlashes(url) + "/" + stripLeadingSlashes(relativePath);
        }
        Logger.getLogger().debug(this, "called listDir. NEW url is: " + url);
        depth++;
        populate();
    }

    private void setRootPath() {
        depth = 0;
        url = null;
    }

    public void commandAction(Command c, Displayable d) {
        if (c == List.SELECT_COMMAND) {
            listDir(getString(getSelectedIndex()));
        } else if (depth > 0 && c == CommandRegistry.back()) {
            navigateUp();
        } else if (d == this && c == CommandRegistry.back()) {
            setRootPath();
            // TODO: ugly/fix
            ((FileSystemInfoScreen) parent).setCurrent();
        } else {
            parentListener.commandAction(c, d);
        }
    }
}

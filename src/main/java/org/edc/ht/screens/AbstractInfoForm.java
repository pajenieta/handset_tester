package org.edc.ht.screens;

import java.util.Vector;

import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

import org.edc.util.log.Logger;

public abstract class AbstractInfoForm extends Form {

    protected Vector debugInfo;

    public AbstractInfoForm(String title) {
        super(title);
    }

    protected void printPropGroup(String groupName, String[] propKeys) {
        append(new StringItem(groupName, ""));
        for (int i = 0; i < propKeys.length; i++) {
            append(propKeys[i], System.getProperty(propKeys[i]));
        }
    }

    protected void append(String key, int val) {
        append(key, new Integer(val));
    }

    protected void append(String key, Object val) {
        append(new StringItem(key + "=", val == null ? "null" : val.toString()));
        if (Logger.getLogger().isDebug()) {
            if (debugInfo == null)
                debugInfo = new Vector();
            debugInfo.addElement(key + "=" + val);
        }
    }

    public void onSetCurrent() {
        Logger logger = Logger.getLogger();
        if (logger.isDebug()) {
            for (int i = 0; i < debugInfo.size(); i++)
                logger.log(this, Logger.DEBUG, debugInfo.elementAt(i).toString());
        }
    }

}

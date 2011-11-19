package org.edc.ht.screens;

import java.util.Vector;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

import org.edc.util.log.Logger;

/**
 * A screen that displays phone tech specs
 * 
 * @author greg
 */
public class PhoneSpecs extends Form implements OnSetCurrentEventObserver {

    Vector debugInfo;

    public PhoneSpecs(Display display) {
        super("Phone Specs");
        String[] propKeys = getPropKeys();
        for (int i = 0; i < propKeys.length; i++) {
            append(propKeys[i], System.getProperty(propKeys[i]));
        }
        addDisplayInfo(display);
    }

    private final void addDisplayInfo(Display display) {
        append(new StringItem("Display Settings", ""));
        append("ListElement Image", getBestDimensions(display, Display.LIST_ELEMENT));
        append("Alert Image", getBestDimensions(display, Display.ALERT));
        append("ChoiceGroup Image", getBestDimensions(display, Display.CHOICE_GROUP_ELEMENT));
    }

    private void append(String key, String val) {
        append(new StringItem(key + "=", val));
        if (Logger.getLogger().isDebug()) {
            if (debugInfo == null)
                debugInfo = new Vector();
            debugInfo.addElement(key + "=" + val);
        }
    }

    static final String getBestDimensions(Display display, int imageType) {
        int x = display.getBestImageWidth(imageType);
        int y = display.getBestImageHeight(imageType);
        return x + "x" + y;
    }

    // http://www.developer.nokia.com/Community/Wiki/How_to_get_IMEI_in_Java_ME
    // http://www.developer.nokia.com/Community/Wiki/Getting_IMEI_and_IMSI_in_Java_ME
    // see: http://developers.sun.com/mobility/midp/questions/properties/

    protected String[] getPropKeys() {
        return new String[] {
                "IMEI",
                "com.samsung.imei",
                "com.nokia.mid.imei",
                "microedition.configuration",
                "microedition.profiles",
                "microedition.platform",
                "fileconn.dir.memorycard",
                "audio.encodings",
                "microedition.hostname",
                "microedition.msa.version",
                "microedition.jtwi.version",
                "microedition.locale"
        };
    }

    public void onSetCurrent() {
        Logger logger = Logger.getLogger();
        if (logger.isDebug()) {
            for (int i = 0; i < debugInfo.size(); i++)
                logger.log(this, Logger.DEBUG, debugInfo.elementAt(i).toString());
        }
    }

}

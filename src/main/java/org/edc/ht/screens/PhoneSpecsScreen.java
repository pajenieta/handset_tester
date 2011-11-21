package org.edc.ht.screens;

import java.util.Enumeration;
import java.util.Hashtable;
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
public class PhoneSpecsScreen extends Form implements OnSetCurrentEventObserver {

    Vector debugInfo;

    public PhoneSpecsScreen(Display display) {
        super("Phone Specs");
        append("Device Vendor", getDeviceVendor());
        addPhoneSystemProps();
        addDisplayInfo(display);
        addFileSystemInfo();
    }

    private Hashtable vendorClassMap() {
        Hashtable ret = new Hashtable();

        // this class is supposedly availablsamsung devices, but it is not available
        // in the samsung j2me emulator sdk
        ret.put("com.samsung.util.Vibration", "SAMSUNG");
        ret.put("com.motorola.multimedia.Vibrator", "MOTOROLA");
        return ret;
    }

    private String getDeviceVendor() {
        String platform = System.getProperty("microedition.platform");
        // supposedly, motorola, samsung, and maybe some other vendors return "j2me"
        if (platform != null && "j2me".equals(platform.toLowerCase())) {
            Hashtable lookup = vendorClassMap();
            for (Enumeration e = lookup.keys(); e.hasMoreElements();) {
                String vendorClassName = (String) e.nextElement();
                try {
                    Logger.getLogger().debug(this, "Testing for class: " + vendorClassName);
                    Class.forName(vendorClassName);
                    return (String) lookup.get(vendorClassName);
                } catch (Exception ex) {
                }
            }
        }
        return platform;
    }

    private void addPhoneSystemProps() {
        printPropGroup("Basic Properties", getPropKeys());
    }

    private void printPropGroup(String groupName, String[] propKeys) {
        append(new StringItem(groupName, ""));
        for (int i = 0; i < propKeys.length; i++) {
            append(propKeys[i], System.getProperty(propKeys[i]));
        }
    }

    private void addFileSystemInfo() {
        printPropGroup("Filesystem Info", fsProps());
    }

    private String[] fsProps() {
        String[] basePropKeys = {
                "fileconn.dir.private",
                "fileconn.dir.memorycard",
                "fileconn.dir.photos",
                "fileconn.dir.videos",
                "fileconn.dir.graphics",
                "fileconn.dir.tones",
                "fileconn.dir.music",
                "fileconn.dir.recordings"
        };
        String[] ret = new String[basePropKeys.length * 2];
        for (int i = 0; i < basePropKeys.length; i++) {
            ret[i * 2] = basePropKeys[i];
            ret[(i * 2) + 1] = basePropKeys[i] + ".name";
        }
        return ret;
    }

    private final void addDisplayInfo(Display display) {
        append(new StringItem("Display Settings", ""));
        append("ListElement Image", getBestDimensions(display, Display.LIST_ELEMENT));
        append("Alert Image", getBestDimensions(display, Display.ALERT));
        append("ChoiceGroup Image", getBestDimensions(display, Display.CHOICE_GROUP_ELEMENT));

        // Color?
        append("Is color", new Boolean(display.isColor()));
        append("Num colors", display.numColors());
        append("Num alpha", display.numAlphaLevels());

        // get information about device color prefs. Note that some devices
        // return invalid output for these
        append("COLOR_BACKGROUND", display.getColor(Display.COLOR_BACKGROUND));
        append("COLOR_FOREGROUND", display.getColor(Display.COLOR_FOREGROUND));
        append("COLOR_BORDER", display.getColor(Display.COLOR_BORDER));
        append("COLOR_HIGHLIGHTED_BACKGROUND", display.getColor(Display.COLOR_HIGHLIGHTED_BACKGROUND));
        append("COLOR_HIGHLIGHTED_FOREGROUND", display.getColor(Display.COLOR_HIGHLIGHTED_FOREGROUND));
        append("COLOR_HIGHLIGHTED_BORDER", display.getColor(Display.COLOR_HIGHLIGHTED_BORDER));
    }

    private void append(String key, int val) {
        append(key, new Integer(val));
    }

    private void append(String key, Object val) {
        append(new StringItem(key + "=", val == null ? "null" : val.toString()));
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

    /*
     * TODO: rather than print the IMEI, I should just (in info or error mode) print whether or not
     * I was able to get the IMEI by testing that the value of the property was not null and
     * contained maybe 3 consecutive digits.
     */
    protected String[] getPropKeys() {
        return new String[] {
                "microedition.platform",
                "microedition.configuration",
                "microedition.profiles",
                "audio.encodings",
                "microedition.hostname",
                "microedition.msa.version",
                "microedition.jtwi.version",
                "microedition.locale"
        };
    }

    private String[] imeiKeys() {
        return new String[] {
                "IMEI",
                "com.samsung.imei",
                "com.nokia.mid.imei"
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

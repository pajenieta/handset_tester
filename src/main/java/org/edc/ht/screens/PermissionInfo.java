package org.edc.ht.screens;

import javax.microedition.midlet.MIDlet;

public class PermissionInfo extends AbstractInfoForm implements OnSetCurrentEventObserver {

    public PermissionInfo(MIDlet parentMidlet) {
        super("Permissions");
        appendPermissionGroup(parentMidlet, "Filesystem", filePermissions());
        appendPermissionGroup(parentMidlet, "Bluetooth", bluetoothPermissions());
    }

    protected void appendPermissionGroup(MIDlet midlet, String groupName, String[] permissions) {
        append(groupName, "");
        for (int i = 0; i < permissions.length; i++)
            append(permissions[i], midlet.checkPermission(permissions[i]));
    }

    private String[] bluetoothPermissions() {
        return new String[] {
                "javax.microedition.io.Connector.comm",
                "javax.microedition.io.Connector.bluetooth.client",
                "javax.microedition.io.Connector.bluetooth.server",
                "javax.microedition.io.Connector.obex.client",
                "javax.microedition.io.Connector.obex.client.tcp",
                "javax.microedition.io.Connector.obex.server",
                "javax.microedition.io.Connector.obex.server.tcp"
        };
    }

    private String[] filePermissions() {
        return new String[] {
                "javax.microedition.io.Connector.file.read",
                "javax.microedition.io.Connector.file.write"
        };
    }
}

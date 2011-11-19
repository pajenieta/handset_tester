package org.edc.ht.screens;


/**
 * It is lame that Displayable does not implement an interface...
 */
public interface OnSetCurrentEventObserver {

    /**
     * A callback that will get fired when the screen is set as current
     */
    void onSetCurrent();
}

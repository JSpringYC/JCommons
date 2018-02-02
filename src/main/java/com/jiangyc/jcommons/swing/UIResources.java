package com.jiangyc.jcommons.swing;

import javax.swing.*;

public class UIResources {
    /** Resources bundle key */
    public static final String RESOURCES_BUNDLE_KEY = "com.jiangyc.jcommons.swing.resources.bundle";

    public static void load() {
        UIManager.getDefaults().addResourceBundle(RESOURCES_BUNDLE_KEY);
    }
}

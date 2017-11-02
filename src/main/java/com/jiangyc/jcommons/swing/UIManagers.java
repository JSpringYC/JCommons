package com.jiangyc.jcommons.swing;

import javax.swing.*;
import java.util.ResourceBundle;

public class UIManagers {

    private static final UIDefaults defaults = new UIDefaults();;

    static {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("com.jiangyc.jcommons.swing.bundle");

            for (String key : bundle.keySet()) {
                defaults.put(key, bundle.getObject(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object get(Object key) {
        Object value = defaults.get(key);

        return (value == null) ? UIManager.get(key) : value;
    }
}

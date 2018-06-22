/*
 * JCommons
 * Copyright (C) 2018  姜永春
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jiangyc.jcommons.swing;

import javax.swing.*;

/**
 * Store custom UI resources such as component information, strings, etc.
 */
public class UIDefaults2 extends UIDefaults {
    /** Single instance */
    private static UIDefaults2 instance = new UIDefaults2();

    /** Resources bundle key */
    public static final String RESOURCES_BUNDLE_KEY = "com.jiangyc.jcommons.swing.resources.bundle";

    /**
     * Single instance
     * @return Single instance
     */
    public static UIDefaults2 getInstance() {
        return instance;
    }

    /**
     * Returns the value for key.
     * @param key the desired key
     * @param cls the class of value
     * @param <T> the value type
     * @return the value for <code>key</code>
     * @see #get(Object)
     */
    @SuppressWarnings("unchecked")
    public <T> T get(Object key, Class<T> cls) {
        return (T) get(key);
    }
}

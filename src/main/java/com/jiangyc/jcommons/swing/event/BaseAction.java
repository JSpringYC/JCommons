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

package com.jiangyc.jcommons.swing.event;

import com.jiangyc.jcommons.swing.UIDefaults2;
import lombok.Data;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Basic implementation of Action.
 */
@Data
public class BaseAction implements com.jiangyc.jcommons.swing.event.Action {
    /** The UIDefaults2 class. */
    private UIDefaults2 uiDefaults2 = UIDefaults2.getInstance();

    /**
     * The ID of this Action
     */
    private String id;

    /**
     * The text of this Action.
     */
    private String text;

    /**
     * The parent of this Action.
     */
    private String parent;

    /**
     * The sort of this Action.
     */
    private int sort;

    /**
     * The mnemonic of this Action.
     */
    private int mnemonic;

    /**
     * The type of this Action.
     */
    private String type;

    /**
     * The default constructor
     */
    public BaseAction() {
    }

    /**
     * Initialize with given data
     * @param id The ID of this Action
     * @param text The text of this Action.
     * @param parent The parent of this Action.
     * @param sort The sort number of this Action.
     * @param mnemonic The mnemonic of this Action.
     */
    public BaseAction(String id, String text, String parent, int sort, int mnemonic) {
        this.id = id;
        this.text = text;
        this.parent = parent;
        this.sort = sort;
        this.mnemonic = mnemonic;
    }

    /**
     * Initialize with given data
     * @param id The ID of this Action
     * @param text The text of this Action.
     * @param parent The parent of this Action.
     * @param sort The sort number of this Action.
     * @param type The type of this Action.
     */
    public BaseAction(String id, String text, String parent, int sort, String type) {
        this.id = id;
        this.text = text;
        this.parent = parent;
        this.sort = sort;
        this.type = type;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public KeyStroke getKeyStroke() {
        return null;
    }

    @Override
    public int compareTo(Action o) {
        return Integer.compare(sort, o.getSort());
    }

    /**
     * Get UIDefaults2 class.
     * @return UIDefaults2 class.
     */
    public UIDefaults2 getUIDefaults2() {
        return uiDefaults2;
    }

    /**
     * Returns the value for key.
     * @param key the desired key
     * @param cls the class of value
     * @param <T> the value type
     * @return the value for <code>key</code>
     * @see UIDefaults2#get(Object)
     */
    public <T> T get(Object key, Class<T> cls) {
        return getUIDefaults2().get(key, cls);
    }
}

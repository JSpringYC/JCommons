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

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Usually a component that can be displayed on the interface, such as menus, menu items, context menus, etc.
 */
public interface Action extends Comparable<Action> {

    /**
     * Get the ID of this Action
     * @return The ID of this Action
     */
    String getId();

    /**
     * Get the name of this Action
     * @return The name of this Action.
     */
    String getText();

    /**
     * Get the parent of this Action
     * @return The parent of this Action.
     */
    String getParent();

    /**
     * Get the serial number of this Action
     * @return The serial number of this Action.
     */
    int getSort();

    /**
     * Get mnemonics for this Action
     * @return Mnemonics for this Action
     */
    int getMnemonic();

    /**
     * Invoked when an action occurs.
     * @param e the action event
     */
    void actionPerformed(ActionEvent e);

    /**
     * Get the type of this Action
     * @return The type of this Action
     */
    String getType();

    /**
     * Get KeyStroke for this Action
     * @return the KeyStroke
     */
    KeyStroke getKeyStroke();
}

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

import java.util.List;

/**
 * Used to scan all the actions registered in the project.
 *
 * You need to implement this interface. The framework will load this class as {@code ServiceLoader} and automatically
 * retrieve all the Actions registered there.
 */
public interface ActionScanner {

    /**
     * Get the Actions in the project. You need to implement this method to register the Actions in the project.
     * @return The Actions in the project.
     */
    List<Action> obtain();
}

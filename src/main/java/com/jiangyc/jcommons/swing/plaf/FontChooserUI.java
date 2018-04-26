/*
 * JCommons
 * Copyright (C) 2018 姜永春
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

package com.jiangyc.jcommons.swing.plaf;

import com.jiangyc.jcommons.swing.JFontChooser;

import javax.swing.JButton;
import javax.swing.plaf.ComponentUI;

public abstract class FontChooserUI extends ComponentUI {

    public abstract String getApproveButtonText(JFontChooser fc);

    public abstract String getApproveButtonTooltipText(JFontChooser fc);

    public abstract String getDialogTitle(JFontChooser fc);

    /**
     * Returns default button for current <code>LookAndFeel</code>.
     * <code>JFileChooser</code> will use this button as default button
     * for dialog windows.
     *
     * @param fc JFontChooser instance
     * @return The default button
     */
    public JButton getDefaultButton(JFontChooser fc) {
        return null;
    }
}

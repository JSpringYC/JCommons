package com.jiangyc.jcommons.swing;

import javax.accessibility.Accessible;
import javax.swing.*;

/**
 * <code>JFontChooser</code> provides a simple mechanism for the user to
 * choose a file.
 * <p>
 * <p>
 * The following code pops up a font chooser for the user customer font.
 * <pre>
 *     JFontChooser fontChooser = new JFontChooser();
 *     Font customerFont = new Font("Consolas");
 *     fontChooser.setFont(customerFont);
 *     int returnVal = chooser.showOpenDialog(parent);
 *     if(returnVal == JFontChooser.APPROVE_OPTION) {
 *       System.out.println("You chose font: " +
 *            chooser.getSelectedFont().getName());
 *     }
 * </pre>
 * </p>
 * <strong>Warning:</strong> Swing is not thread safe. For more
 * information see <a href="package-summary.html#threading">Swing's Threading
 * Policy</a>.
 *
 * @author JSpringYC
 */
public class JFontChooser extends JComponent implements Accessible {

    @Override
    public String getUIClassID() {
        return "FontChooserUI";
    }

    public static void main(String[] args) {
        JFileChooser jfc;
    }
}

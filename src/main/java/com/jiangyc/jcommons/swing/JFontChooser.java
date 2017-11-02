package com.jiangyc.jcommons.swing;

import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;

/**
 * <code>JFontChooser</code> 为用户提供了一种简单的机制来选择字体。
 * <p>
 * 以下代码弹出自定义的字体的字体选择器。
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
 * <strong>警告:</strong> Swing不是线程安全的。有关更多信息，
 * 请参阅<a href="package-summary.html#threading">
 * Swing的线程策略</a>.
 * @author JSpringYC
 */
public class JFontChooser extends JComponent implements Accessible {

    /**
     * @see #getUIClassID
     * @see #readObject
     */
    private static final String uiClassID = "FontChooserUI";

    // ********************************
    // ********* 对话框返回值 ***********
    // ********************************

    /**
     * Return value if cancel is chosen.
     */
    public static final int CANCEL_OPTION = 1;

    /**
     * Return value if approve (yes, ok) is chosen.
     */
    public static final int APPROVE_OPTION = 0;

    /**
     * Return value if an error occurred.
     */
    public static final int ERROR_OPTION = -1;

    // ******************************
    // *********** 实例变量 **********
    // ******************************

    private String dialogTitle = null;
    private String approveButtonText = null;
    private String approveButtonToolTipText = null;
    private int approveButtonMnemonic = 0;

    private JDialog dialog = null;
    private int returnValue = ERROR_OPTION;
    private JComponent accessory = null;

    private boolean fontNameEditable = false;
    private boolean fontStyleEditable = false;
    private boolean fontSizeEditable = false;

    /**
     * 加载特定的UI资源
     */
    static {
        UIManager.put("FontChooserUI", "com.jiangyc.jcommons.swing.FontChooserUI");
    }

    @Override
    public String getUIClassID() {
        return uiClassID;
    }

    @Override
    public void updateUI() {
        setUI(UIManager.getUI(this));
    }

    /**
     * 获取实现此组件的L&F的UI对象。
     *
     * @return FontChooserUI L&F的FontChooserUI对象
     */
    public FontChooserUI getUI() {
        return (FontChooserUI) ui;
    }

    /**
     * 获取实现此组件的L&F的UI对象。
     * @param newUI
     */
    public void setUI(FontChooserUI newUI) {
        super.setUI(newUI);
    }

    public JFontChooser() {
        updateUI();
    }
}

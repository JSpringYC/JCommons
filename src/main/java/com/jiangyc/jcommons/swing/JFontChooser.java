package com.jiangyc.jcommons.swing;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.FileChooserUI;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

    // **********************************
    // ***** JFileChooser properties *****
    // **********************************
    /** Identifies change in the text on the approve (yes, ok) button. */
    public static final String APPROVE_BUTTON_TEXT_CHANGED_PROPERTY = "ApproveButtonTextChangedProperty";
    /** Identifies change in the tooltip text on the approve (yes, ok) button. */
    public static final String APPROVE_BUTTON_TOOLTIP_TEXT_CHANGED_PROPERTY = "ApproveButtonTooltipTextChangedProperty";
    /** Identifies change in the mnemonic on the approve (yes, ok) button. */
    public static final String APPROVE_BUTTON_MNEMONIC_CHANGED_PROPERTY = "ApproveButtonMnemonicChangedProperty";
    /** Identifies a change in the dialog title. */
    public static final String DIALOG_TITLE_CHANGED_PROPERTY = "DialogTitleChangedProperty";

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
        UIResources.load();
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

    /**
     * Default <code>JFontChooser</code> constructor.
     */
    public JFontChooser() {
        updateUI();
    }

    /**
     * Creates and returns a new <code>JDialog</code> wrapping
     * <code>this</code> centered on the <code>parent</code>
     * in the <code>parent</code>'s frame.
     *
     * @param parent the parent component of the dialog;
     *                  can be <code>null</code>
     * @return a new <code>JDialog</code> containing this instance
     */
    public JDialog createDialog(Component parent) {
        FontChooserUI ui = getUI();
        String title = ui.getDialogTitle(this);
        putClientProperty(AccessibleContext.ACCESSIBLE_DESCRIPTION_PROPERTY,
                title);

        JDialog dialog;
        if (parent != null) {
            Window window = SwingUtilities.getWindowAncestor(parent);
            if (window instanceof Frame) {
                dialog = new JDialog((Frame)window, title, true);
            } else {
                dialog = new JDialog((Dialog)window, title, true);
            }
        } else {
            dialog = new JDialog((Frame)null, title, true);
        }

        dialog.setComponentOrientation(this.getComponentOrientation());

        Container contentPane = dialog.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(this, BorderLayout.CENTER);

        if (JDialog.isDefaultLookAndFeelDecorated()) {
            boolean supportsWindowDecorations =
                    UIManager.getLookAndFeel().getSupportsWindowDecorations();
            if (supportsWindowDecorations) {
                dialog.getRootPane().setWindowDecorationStyle(JRootPane.FONT_CHOOSER_DIALOG);
            }
        }
        dialog.pack();
        dialog.setLocationRelativeTo(parent);

        return dialog;
    }

    public int showDialog(Component parent) {

        if (dialog != null) {
            // Prevent to show second instance of dialog if the previous one still exists
            return JFontChooser.ERROR_OPTION;
        }

        if(approveButtonText != null) {
            setApproveButtonText(approveButtonText);
        }

        dialog = createDialog(parent);
        dialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                returnValue = CANCEL_OPTION;
            }
        });

        dialog.setVisible(true);
        firePropertyChange("JFontChooserDialogIsClosingProperty", dialog, null);

        // Remove all components from dialog. The MetalFileChooserUI.installUI() method (and other LAFs)
        // registers AWT listener for dialogs and produces memory leaks. It happens when
        // installUI invoked after the showDialog method.
        dialog.getContentPane().removeAll();
        dialog.dispose();
        dialog = null;
        return returnValue;
    }

    // **************************
    // ***** Dialog Options *****
    // **************************

    /**
     * 获取字体选择对话框的标题
     * @return 字体选择对话框的标题
     */
    public String getDialogTitle() {
        return dialogTitle;
    }

    /**
     * 设置字体选择对话框的标题
     * @param dialogTitle 要设置的字体选择对话框的标题
     */
    public void setDialogTitle(String dialogTitle) {
        String oldValue = this.dialogTitle;
        this.dialogTitle = dialogTitle;
        if(dialog != null) {
            dialog.setTitle(dialogTitle);
        }
        firePropertyChange(DIALOG_TITLE_CHANGED_PROPERTY, oldValue, dialogTitle);
    }

    // ************************************
    // ***** JFontChooser View Options *****
    // ************************************

    /**
     * Sets the text used in the <code>ApproveButton</code> in the
     * <code>FontChooserUI</code>.
     *
     * @param approveButtonText the text used in the <code>ApproveButton</code>
     *
     * @see #getApproveButtonText
     * @see #showDialog
     */
    // PENDING(jeff) - have ui set this on dialog type change
    public void setApproveButtonText(String approveButtonText) {
        if(this.approveButtonText == approveButtonText) {
            return;
        }
        String oldValue = this.approveButtonText;
        this.approveButtonText = approveButtonText;
        firePropertyChange(APPROVE_BUTTON_TEXT_CHANGED_PROPERTY, oldValue, approveButtonText);
    }

    /**
     * Returns the text used in the <code>ApproveButton</code> in the
     * <code>FontChooserUI</code>.
     * If <code>null</code>, the UI object will determine the button's text.
     *
     * Typically, this would be "Ok".
     *
     * @return the text used in the <code>ApproveButton</code>
     *
     * @see #setApproveButtonText
     * @see #showDialog
     */
    public String getApproveButtonText() {
        return approveButtonText;
    }

    public String getApproveButtonToolTipText() {
        return approveButtonToolTipText;
    }

    public void setApproveButtonToolTipText(String approveButtonToolTipText) {
        if (this.approveButtonToolTipText == approveButtonToolTipText) {
            return;
        }
        String oldValue = this.approveButtonToolTipText;
        this.approveButtonToolTipText = approveButtonToolTipText;
        firePropertyChange(APPROVE_BUTTON_TOOLTIP_TEXT_CHANGED_PROPERTY, oldValue, approveButtonToolTipText);
    }

    public int getApproveButtonMnemonic() {
        return approveButtonMnemonic;
    }

    public void setApproveButtonMnemonic(int approveButtonMnemonic) {
        this.approveButtonMnemonic = approveButtonMnemonic;

        if (this.approveButtonMnemonic == approveButtonMnemonic) {
            return;
        }
        int oldValue = this.approveButtonMnemonic;
        this.approveButtonMnemonic = approveButtonMnemonic;
        firePropertyChange(APPROVE_BUTTON_MNEMONIC_CHANGED_PROPERTY, oldValue, approveButtonMnemonic);
    }
}

package com.jiangyc.jcommons.swing;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;

/**
 * <code>JFontChooser</code>可插拔的外观和界面。
 */
public class FontChooserUI extends ComponentUI {

    private JFontChooser fontChooser = null;

    private String dialogTitle = null;
    private String approveButtonText = null;
    private String approveButtonTooltipText = null;
    private String cancelButtonText = null;
    private String cancelButtonTooltipText = null;

    private String fontNameText = null;
    private String fontStyleText = null;
    private String fontSizeText = null;

    /**
     * 静态的实例化FontChooserUI的方法，Swing会通过反射技术调用此方法获取组件对应的UI。
     * @return 实现此L&F的UI对象。
     *
     * @see UIDefaults#getUI(JComponent)
     */
    public static FontChooserUI createUI(JComponent c) {
        return new FontChooserUI();
    }

    @Override
    public void installUI(JComponent c) {
        fontChooser = (JFontChooser) c;

        installDefaults(fontChooser);
        installComponents(fontChooser);
        installListeners(fontChooser);
    }

    private void installComponents(JFontChooser fontChooser) {
    }

    private void installListeners(JFontChooser jfc) {
    }

    private void installDefaults(JFontChooser jfc) {
        dialogTitle = (String) UIManagers.get("FontChooser.DialogTitle");
        approveButtonText = (String) UIManagers.get("approveButtonText");
        approveButtonTooltipText = (String) UIManagers.get("approveButtonTooltipText");
        cancelButtonText = (String) UIManagers.get("cancelButtonText");
        cancelButtonTooltipText = (String) UIManagers.get("cancelButtonTooltipText");

        fontNameText = (String) UIManagers.get("FontChooser.fontNameText");
        fontStyleText = (String) UIManagers.get("FontChooser.fontStyleText");
        fontSizeText = (String) UIManagers.get("FontChooser.fontSizeText");
    }
}

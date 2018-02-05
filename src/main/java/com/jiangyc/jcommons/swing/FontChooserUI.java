package com.jiangyc.jcommons.swing;

import com.jiangyc.jcommons.FontModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.beans.PropertyChangeListener;

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
    private String fontPreviewText = null;

    private PropertyChangeListener propertyChangeListener;
    private FontModel model;

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

    private void installComponents(JFontChooser fc) {
        contentPane = new JPanel();
        // Start of add components to Font Chooser Panel
        fc.setLayout(new BorderLayout());
        fc.add(contentPane, BorderLayout.CENTER);
        // End of add components to Font Chooser Panel
        contentPane.setLayout(new BorderLayout(0, 0));
        fontPane = new JPanel();
        fontPane.setLayout(new GridBagLayout());
        contentPane.add(fontPane, BorderLayout.CENTER);
        fontSelectPane = new JPanel();
        fontSelectPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.6;
        gbc.fill = GridBagConstraints.BOTH;
        fontPane.add(fontSelectPane, gbc);
        fontNamePane = new JPanel();
        fontNamePane.setLayout(new BorderLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        fontSelectPane.add(fontNamePane, gbc);
        fontNamePane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Name"));
        fontNameField = new JTextField();
        fontNameField.setText("宋体");
        fontNamePane.add(fontNameField, BorderLayout.NORTH);
        final JScrollPane scrollPane1 = new JScrollPane();
        fontNamePane.add(scrollPane1, BorderLayout.CENTER);
        fontNameList = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        defaultListModel1.addElement("list1");
        defaultListModel1.addElement("list2");
        defaultListModel1.addElement("list3");
        defaultListModel1.addElement("list4");
        defaultListModel1.addElement("list5");
        defaultListModel1.addElement("list6");
        fontNameList.setModel(defaultListModel1);
        scrollPane1.setViewportView(fontNameList);
        fontStylePane = new JPanel();
        fontStylePane.setLayout(new BorderLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        fontSelectPane.add(fontStylePane, gbc);
        fontStylePane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Style"));
        fontStyleField = new JTextField();
        fontStyleField.setText("常规");
        fontStylePane.add(fontStyleField, BorderLayout.NORTH);
        final JScrollPane scrollPane2 = new JScrollPane();
        fontStylePane.add(scrollPane2, BorderLayout.CENTER);
        fontStyleList = new JList();
        final DefaultListModel defaultListModel2 = new DefaultListModel();
        defaultListModel2.addElement("常规");
        defaultListModel2.addElement("粗体");
        defaultListModel2.addElement("斜体");
        defaultListModel2.addElement("粗体+斜体");
        fontStyleList.setModel(defaultListModel2);
        scrollPane2.setViewportView(fontStyleList);
        fontSizePane = new JPanel();
        fontSizePane.setLayout(new BorderLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        fontSelectPane.add(fontSizePane, gbc);
        fontSizePane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Size"));
        fontSizeField = new JTextField();
        fontSizeField.setText("14");
        fontSizePane.add(fontSizeField, BorderLayout.NORTH);
        final JScrollPane scrollPane3 = new JScrollPane();
        fontSizePane.add(scrollPane3, BorderLayout.CENTER);
        fontSizeList = new JList();
        final DefaultListModel defaultListModel3 = new DefaultListModel();
        defaultListModel3.addElement("8");
        defaultListModel3.addElement("10");
        defaultListModel3.addElement("12");
        defaultListModel3.addElement("14");
        fontSizeList.setModel(defaultListModel3);
        scrollPane3.setViewportView(fontSizeList);
        fontPreviewPane = new JPanel();
        fontPreviewPane.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        fontPane.add(fontPreviewPane, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        fontPreviewPane.add(panel1, gbc);
        fontPreviewBorderPane = new JPanel();
        fontPreviewBorderPane.setLayout(new BorderLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.6;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        fontPreviewPane.add(fontPreviewBorderPane, gbc);
        fontPreviewBorderPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Preview"));
        fontPreviewLabel = new JLabel();
        fontPreviewLabel.setHorizontalAlignment(0);
        fontPreviewLabel.setHorizontalTextPosition(0);
        fontPreviewLabel.setText("Label");
        fontPreviewBorderPane.add(fontPreviewLabel, BorderLayout.CENTER);
        actionPane = new JPanel();
        actionPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        contentPane.add(actionPane, BorderLayout.SOUTH);
        approveButton = new JButton();
        approveButton.setText("OK");
        actionPane.add(approveButton);
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        actionPane.add(cancelButton);
        // Start of init components properties
        //   approve button text
        approveButton.setText(getApproveButtonText(fc));
        approveButton.setToolTipText(getApproveButtonTooltipText(fc));
        if (fc.getApproveButtonMnemonic() != 0) {
            approveButton.setMnemonic(fc.getApproveButtonMnemonic());
        }
        //   cancel button text
        cancelButton.setText(cancelButtonText);
        cancelButton.setToolTipText(cancelButtonTooltipText);
        //   the title of titled border
        ((TitledBorder) fontPreviewBorderPane.getBorder()).setTitle(fontPreviewText);
        ((TitledBorder) fontNamePane.getBorder()).setTitle(fontNameText);
        ((TitledBorder) fontStylePane.getBorder()).setTitle(fontStyleText);
        ((TitledBorder) fontSizePane.getBorder()).setTitle(fontSizeText);
        // End of init components properties
    }

    private void installListeners(JFontChooser jfc) {
        propertyChangeListener = createPropertyChangeListener(jfc);
        if(propertyChangeListener != null) {
            jfc.addPropertyChangeListener(propertyChangeListener);
        }
        jfc.addPropertyChangeListener(getModel());
    }

    private void installDefaults(JFontChooser jfc) {
        dialogTitle = (String) UIManager.get("FontChooser.dialogTitle");
        approveButtonText = (String) UIManager.get("approveButtonText");
        approveButtonTooltipText = (String) UIManager.get("approveButtonTooltipText");
        cancelButtonText = (String) UIManager.get("cancelButtonText");
        cancelButtonTooltipText = (String) UIManager.get("cancelButtonTooltipText");

        fontNameText = (String) UIManager.get("FontChooser.fontNameText");
        fontStyleText = (String) UIManager.get("FontChooser.fontStyleText");
        fontSizeText = (String) UIManager.get("FontChooser.fontSizeText");
        fontPreviewText = (String) UIManager.get("FontChooser.fontPreviewText");
    }

    /**
     * 获取当前的对话框标题
     * @return
     */
    public String getDialogTitle(JFontChooser jfc) {
        String title = jfc.getDialogTitle();

        return (title == null) ? dialogTitle : title;
    }

    /**
     * 获取当前的对话框标题
     * @return
     */
    public String getApproveButtonText(JFontChooser jfc) {
        String approveButtonText = jfc.getApproveButtonText();

        return (approveButtonText == null) ? this.approveButtonText : approveButtonText;
    }

    public String getApproveButtonTooltipText(JFontChooser jfc) {
        String approveButtonTooltipText = jfc.getApproveButtonToolTipText();

        return (approveButtonTooltipText == null) ? this.approveButtonTooltipText : approveButtonTooltipText;
    }

    public PropertyChangeListener createPropertyChangeListener(JFontChooser fc) {
        return null;
    }

    public FontModel getModel() {
        if (model == null) {
            model = new FontModel();
        }
        return model;
    }

    /** UI Components */
    private JPanel contentPane;
    private JPanel fontPane;
    private JPanel actionPane;
    private JButton approveButton;
    private JButton cancelButton;
    private JPanel fontPreviewPane;
    private JPanel fontSelectPane;
    private JPanel fontPreviewBorderPane;
    private JLabel fontPreviewLabel;
    private JPanel fontNamePane;
    private JPanel fontStylePane;
    private JPanel fontSizePane;
    private JTextField fontNameField;
    private JTextField fontStyleField;
    private JTextField fontSizeField;
    private JList fontNameList;
    private JList fontStyleList;
    private JList fontSizeList;
}

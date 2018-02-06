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

package com.jiangyc.jcommons.swing.plaf.basic;

import com.jiangyc.jcommons.swing.JFontChooser;
import com.jiangyc.jcommons.swing.plaf.FontChooserUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.util.Locale;

public class BasicFontChooserUI extends FontChooserUI {

    // label text
    private String fontNameText = null;
    private String fontStyleText = null;
    private String fontSizeText = null;
    private String fontPreviewText = null;

    // OK- Cancel Button Text
    private String dialogTitle = null;
    private String approveButtonText = null;
    private String approveButtonTooltipText = null;
    private String cancelButtonText = null;
    private String cancelButtonTooltipText = null;

    private JFontChooser fontchooser = null;

    private PropertyChangeListener propertyChangeListener = null;

    // The accessoryPanel is a container to place the JFileChooser accessory component
    private JPanel accessoryPanel = null;

    /**
     * Creates a {@code BasicFontChooserUI} implementation
     * for the specified component. By default
     * the {@code BasicLookAndFeel} class uses
     * {@code createUI} methods of all basic UIs classes
     * to instantiate UIs.
     *
     * @param c the {@code JFontChooser} which needs a UI
     * @return the {@code BasicFontChooserUI} object
     *
     * @see UIDefaults#getUI(JComponent)
     */
    public static FontChooserUI createUI(JComponent c) {
        return new BasicFontChooserUI((JFontChooser) c);
    }

    public BasicFontChooserUI(JFontChooser fontChooser) {
    }

    @Override
    public void installUI(JComponent c) {
        accessoryPanel = new JPanel(new BorderLayout());
        fontchooser = (JFontChooser) c;

//        createModel();
        installDefaults(fontchooser);
        installComponents(fontchooser);
        installListeners(fontchooser);
        fontchooser.applyComponentOrientation(fontchooser.getComponentOrientation());
    }

    public void uninstallUI(JComponent c) {
//        uninstallListeners(filechooser);
//        uninstallComponents(filechooser);
//        uninstallDefaults(filechooser);

        if(accessoryPanel != null) {
            accessoryPanel.removeAll();
        }

        accessoryPanel = null;
        fontchooser.removeAll();
//
//        handler = null;
    }

    protected void installComponents(JFontChooser fc) {
        accessoryPanel = new JPanel();
        // Start of add components to Font Chooser Panel
        fc.setLayout(new BorderLayout());
        fc.add(accessoryPanel, BorderLayout.CENTER);
        // End of add components to Font Chooser Panel
        accessoryPanel.setLayout(new BorderLayout(0, 0));
        fontPane = new JPanel();
        fontPane.setLayout(new GridBagLayout());
        accessoryPanel.add(fontPane, BorderLayout.CENTER);
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
        accessoryPanel.add(actionPane, BorderLayout.SOUTH);
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

    protected void installListeners(JFontChooser fc) {
//        propertyChangeListener = createPropertyChangeListener(fc);
//        if(propertyChangeListener != null) {
//            jfc.addPropertyChangeListener(propertyChangeListener);
//        }
//        fc.addPropertyChangeListener(getModel());
    }

    protected void installDefaults(JFontChooser fc) {
//        installIcons(fc);
        installStrings(fc);
//        usesSingleFilePane = UIManager.getBoolean("FileChooser.usesSingleFilePane");
//        readOnly           = UIManager.getBoolean("FileChooser.readOnly");
//        TransferHandler th = fc.getTransferHandler();
//        if (th == null || th instanceof UIResource) {
//            fc.setTransferHandler(defaultTransferHandler);
//        }
//        LookAndFeel.installProperty(fc, "opaque", Boolean.FALSE);
    }

    protected void installStrings(JFontChooser fc) {

        Locale l = fc.getLocale();

        dialogTitle = UIManager.getString("FontChooser.dialogTitle", l);
        approveButtonText = UIManager.getString("approveButtonText", l);
        approveButtonTooltipText = UIManager.getString("approveButtonTooltipText", l);
        cancelButtonText = UIManager.getString("cancelButtonText", l);
        cancelButtonTooltipText = UIManager.getString("cancelButtonTooltipText", l);

        fontNameText = UIManager.getString("FontChooser.fontNameText", l);
        fontStyleText = UIManager.getString("FontChooser.fontStyleText", l);
        fontSizeText = UIManager.getString("FontChooser.fontSizeText", l);
        fontPreviewText = UIManager.getString("FontChooser.fontPreviewText", l);
    }

    @Override
    public String getApproveButtonText(JFontChooser fc) {
        String approveButtonText = fc.getApproveButtonText();

        return (approveButtonText == null) ? this.approveButtonText : approveButtonText;
    }

    @Override
    public String getApproveButtonTooltipText(JFontChooser fc) {
        String approveButtonTooltipText = fc.getApproveButtonToolTipText();

        return (approveButtonTooltipText == null) ? this.approveButtonTooltipText : approveButtonTooltipText;
    }

    @Override
    public String getDialogTitle(JFontChooser fc) {
        String title = fc.getDialogTitle();

        return (title == null) ? dialogTitle : title;
    }

    @Override
    public JButton getDefaultButton(JFontChooser fc) {
        return approveButton;
    }

    /** UI Components */
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

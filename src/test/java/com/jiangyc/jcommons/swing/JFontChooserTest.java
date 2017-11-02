package com.jiangyc.jcommons.swing;

import org.junit.Test;

import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.FileChooserUI;

public class JFontChooserTest {

    @Test
    public void test() {
//        JFontChooser jfc = new JFontChooser();
//
//        JFileChooser jfc2;
//        FileChooserUI fcUI;
//        ButtonModel model;
//        ButtonUI buttonUI;
//
//        System.out.println(jfc.getUI());

        Object approveButtonText = UIManagers.get("approveButtonText");
        System.out.println(approveButtonText);
    }
}

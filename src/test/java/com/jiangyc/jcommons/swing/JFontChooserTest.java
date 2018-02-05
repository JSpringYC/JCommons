package com.jiangyc.jcommons.swing;

import org.junit.Test;

import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.FileChooserUI;

public class JFontChooserTest {

    @Test
    public void test() {
        JFontChooser jfc = new JFontChooser();

        jfc.showDialog(null);

        Object approveButtonText = UIManager.get("approveButtonText");
        System.out.println(approveButtonText);
    }
}

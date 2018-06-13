package com.jiangyc.jcommons.swing;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

public class JFontChooserTest {

    @Before
    public void before() throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }

    @Test
    public void test() {
        JFontChooser jfc = new JFontChooser();

        if (jfc.showDialog(null, new Font("微软雅黑", 0, 11)) == JFontChooser.APPROVE_OPTION) {
            System.out.println(jfc.getSelectedFont());
        }
    }
}

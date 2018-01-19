package com.jiangyc.jcommons.swing.resources;

import java.util.ListResourceBundle;

public class bundle_zh_CN extends ListResourceBundle {

    private Object[][] contents = {{"FontChooserUI", "com.jiangyc.jcommons.swing.FontChooserUI"},
            {"FontChooser.dialogTitle", "字体选择"},
            {"approveButtonText", "确定"},
            {"approveButtonTooltipText", "确定"},
            {"cancelButtonText", "取消"},
            {"cancelButtonTooltipText", "取消"},
            {"FontChooser.fontNameText", "名称"},
            {"FontChooser.fontStyleText", "样式"},
            {"FontChooser.fontSizeText", "大小"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}

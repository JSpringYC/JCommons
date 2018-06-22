package com.jiangyc.jcommons.swing.resources;

import java.util.ListResourceBundle;

public class bundle_zh_CN extends ListResourceBundle {

    private Object[][] contents = {{"FontChooser.dialogTitle", "字体选择"},
            {"approveButtonText", "确定"},
            {"approveButtonTooltipText", "确定"},
            {"cancelButtonText", "取消"},
            {"cancelButtonTooltipText", "取消"},
            {"FontChooser.fontNameText", "名称"},
            {"FontChooser.fontStyleText", "样式"},
            {"FontChooser.fontSizeText", "大小"},
            {"FontChooser.fontPreviewText", "预览"},
            {"FontChooser.fontStyle.Normal", "常规"},
            {"FontChooser.fontStyle.Bold", "粗体"},
            {"FontChooser.fontStyle.Italic", "斜体"},
            {"FontChooser.fontStyle.Bold_Italic", "粗体+斜体"},
            {"FontChooser.fontSize.extra", "初号,42,小初,36,一号,26,小一,24,二号,22,小二,18,三号,16,小三,15,四号,14,小四,12,五号,11,小五,9,六号,8,小六,7,七号,6,八号,5"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}

package com.jiangyc.jcommons.swing.resources;

import java.util.ListResourceBundle;

public class bundle extends ListResourceBundle {

    private Object[][] contents = {{"FontChooserUI", "com.jiangyc.jcommons.swing.plaf.basic.BasicFontChooserUI"},
            {"FontChooser.dialogTitle", "Font Chooser"},
            {"approveButtonText", "Ok"},
            {"approveButtonTooltipText", "Ok"},
            {"cancelButtonText", "Cancel"},
            {"cancelButtonTooltipText", "Cancel"},
            {"FontChooser.fontNameText", "Name"},
            {"FontChooser.fontStyleText", "Style"},
            {"FontChooser.fontSizeText", "Size"},
            {"FontChooser.fontPreviewText", "Preview"}};

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}

package com.jiangyc.jcommons.jdbc.crm.annotation;

import lombok.Data;

import java.lang.reflect.Field;

/**
 * @author jiangyc
 */
@Data
public class ColumnDefinded {

    /** 目标类型 */
    private Field targetField;

    /** 列名 */
    private String name;

    /** 是否为主键 */
    private boolean isPrimaryKey;

    /** 是否为非空 */
    private boolean isNotNull;

    /** 是否为不可重复 */
    private boolean isUnique;

    /** 长度 */
    private int size;
}

package com.jiangyc.jcommons.jdbc.bean;

import lombok.Data;

import java.lang.reflect.Field;

/**
 * 用来保存实体类的属性与数据库的列的关联信息的实体类
 *
 * @author jiangyc
 */
@Data
public class Column {

    /** 数据库的列的列名 */
    private String name;

    /** 数据库的列的是否是主键 */
    private boolean isPrimary;

    /** 数据库的列是否非空 */
    private boolean isNotNull;

    /** 数据库的列是否可用 */
    private boolean isUnique;

    /** 数据库的列的最大长度 */
    private int size;

    /** 实体类的<code>Field</code> */
    private Field targetField;
}

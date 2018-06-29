package com.jiangyc.jcommons.jdbc.bean;

import lombok.Data;

import java.util.List;

/**
 * 用来保存实体类与数据库表的关联信息的实体类
 *
 * @author jiangyc
 */
@Data
public class Table {

    /**
     * 实体类对应的数据库表的表名
     */
    private String name;

    /**
     * 实体类对应的数据库表的主键列表
     */
    private List<Column> primarys;

    /**
     * 实体类对应的数据库表的所有列的列表
     */
    private List<Column> columns;

    /**
     * 实体类的<code>Class</code>
     */
    private Class<?> targetClass;
}

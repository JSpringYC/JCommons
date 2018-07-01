package com.jiangyc.jcommons.jdbc.crm.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

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
    private Map<String, Column> primarys;

    /**
     * 实体类对应的数据库表的所有列的列表
     */
    private Map<String, Column> columns;

    /**
     * 实体类的<code>Class</code>
     */
    private Class<?> targetClass;

    /**
     * 向该表中添加一列
     * @param column
     */
    public void addColumn(Column column) {
        if (columns == null) {
            columns = new HashMap<>();
        }
        columns.put(column.getName(), column);

        if (column.isPrimary()) {
            if (primarys == null) {
                primarys = new HashMap<>();
            }
            primarys.put(column.getName(), column);
        }
    }
}

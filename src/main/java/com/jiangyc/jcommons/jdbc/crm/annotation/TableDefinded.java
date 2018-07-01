package com.jiangyc.jcommons.jdbc.crm.annotation;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiangyc
 */
@Data
public class TableDefinded {

    /** 目标类型 */
    private Class<?> targetClass;

    /** 表名 */
    private String name;

    /** 所有表字段 */
    private Map<String, ColumnDefinded> columnMap;

    /** 所有主键字段 */
    private Map<String, ColumnDefinded> primaryMap;

    /**
     * 添加一列
     * @param columnDefinded 列信息
     */
    public void addColumnDefinded(ColumnDefinded columnDefinded) {
        if (columnMap == null) {
            columnMap = new ConcurrentHashMap<>();
        }
        columnMap.put(columnDefinded.getName(), columnDefinded);

        if (!columnDefinded.isPrimaryKey()) {
            return;
        }

        if (primaryMap == null) {
            primaryMap = new ConcurrentHashMap<>();
        }
        primaryMap.put(columnDefinded.getName(), columnDefinded);
    }

    /**
     * 获取一列
     * @param column 要获取的列的列名
     */
    public ColumnDefinded getColumnDefinded(String column) {
        return columnMap == null ? null : columnMap.get(column);
    }
}

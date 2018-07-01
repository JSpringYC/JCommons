package com.jiangyc.jcommons.jdbc.crm.annotation;

import com.jiangyc.jcommons.util.Strings;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <code>@Table</code>注解解析器
 * @author jiangyc
 */
public abstract class TableParser {
    /**
     * 存放数据库表映射的集合
     */
    private static final Map<Class<?>, TableDefinded> TABLE_DEFINDED_MAP = new ConcurrentHashMap<>();

    /**
     * 解析给定的类型，并返回数据库表映射
     * @param targetClass 要解析的类型
     * @return 数据库表映射
     */
    public static TableDefinded parse(Class<?> targetClass) {
        TableDefinded definded = TABLE_DEFINDED_MAP.get(targetClass);
        if (definded != null) {
            return definded;
        }

        if (!targetClass.isAnnotationPresent(Table.class)) {
            return null;
        }

        Table table = targetClass.getAnnotation(Table.class);
        definded.setTargetClass(targetClass);
        definded.setName(Strings.isBlank(table.value()) ? targetClass.getSimpleName() : table.value());

        ColumnDefinded columnDefinded;
        Column column;
        for (Field field : targetClass.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            }

            columnDefinded = new ColumnDefinded();
            columnDefinded.setTargetField(field);
            column = field.getAnnotation(Column.class);
            columnDefinded.setName(Strings.isBlank(column.value()) ? field.getName() : column.value());
            columnDefinded.setNotNull(column.notNull());
            columnDefinded.setPrimaryKey(column.primary());
            columnDefinded.setSize(column.size());
            columnDefinded.setUnique(column.unique());

            definded.addColumnDefinded(columnDefinded);
        }

        TABLE_DEFINDED_MAP.put(targetClass, definded);
        return definded;
    }
}

package com.jiangyc.jcommons.jdbc.crm;

import com.jiangyc.jcommons.jdbc.Mapper;
import com.jiangyc.jcommons.jdbc.crm.annotation.ColumnDefinded;
import com.jiangyc.jcommons.jdbc.crm.annotation.TableDefinded;
import com.jiangyc.jcommons.jdbc.crm.annotation.TableParser;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 生成Mapper的工厂
 * @author jiangyc
 */
public class MapperFactory {
    /**
     * 类型与Mapper的集合
     */
    private static final Map<Class<?>, Mapper<?>> CLASS_MAPPER_MAP = new ConcurrentHashMap<>();

    /**
     * 获取Mapper
     * @param targetClass 目标类型
     * @param <T> 目标类型
     * @return Mapper
     */
    public static <T> Mapper<T> getMapper(Class<T> targetClass) {
        Mapper<T> mapper = (Mapper<T>) CLASS_MAPPER_MAP.get(targetClass);
        if (mapper != null) {
            return mapper;
        }

        TableDefinded tableDefinded = TableParser.parse(targetClass);
        if (tableDefinded == null) {
            return null;
        }

        mapper = (rs, row) -> {
            T t = null;

            try {
                t = targetClass.newInstance();

                if (tableDefinded.getColumnMap() == null) {
                    return t;
                }

                ColumnDefinded columnDefinded;
                Field field;
                Class<?> fieldClass;
                for (String column : tableDefinded.getColumnMap().keySet()) {
                    columnDefinded = tableDefinded.getColumnDefinded(column);

                    field = columnDefinded.getTargetField();
                    fieldClass = field.getType();

                    if (fieldClass == int.class) {
                        field.setInt(t, rs.getInt(column));
                    } else if (fieldClass == byte.class) {
                        field.setByte(t, rs.getByte(column));
                    } else if (fieldClass == short.class) {
                        field.setShort(t, rs.getShort(column));
                    } else if (fieldClass == long.class) {
                        field.setLong(t, rs.getLong(column));
                    } else if (fieldClass == boolean.class) {
                        field.setBoolean(t, rs.getBoolean(column));
                    } else if (fieldClass == float.class) {
                        field.setFloat(t, rs.getFloat(column));
                    } else if (fieldClass == double.class) {
                        field.setDouble(t, rs.getDouble(column));
                    } else {
                        field.set(t, rs.getString(column));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return t;
        };
        CLASS_MAPPER_MAP.put(targetClass, mapper);
        return mapper;
    }
}

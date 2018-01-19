package com.jiangyc.jcommons.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code ResultSet}中某行到实体类的映射的主接口。
 */
public interface RowMapper<T> {

    /**
     * 将{@code ResultSet}的某一行映射成一个实体类。
     * @param rs 数据库结果集
     * @return 转换后的实体类
     */
    T mapRow(ResultSet rs, int row) throws SQLException;

    /**
     * 将{@code ResultSet}的映射成一个实体类的集合。
     * @param rs 数据库结果集
     * @return 转换后的实体类集合
     */
    default List<T> mapAsList(ResultSet rs) throws SQLException {

        int row = 1;
        List<T> tList = new ArrayList<>();
        while (rs.next()) {
            T t = mapRow(rs, row);
            row ++;
            tList.add(t);
        }
        return tList;
    }
}

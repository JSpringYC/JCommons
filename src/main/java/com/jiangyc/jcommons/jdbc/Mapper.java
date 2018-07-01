package com.jiangyc.jcommons.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code ResultSet}到实体类的映射的主接口。
 * @author jiangyc
 */
public interface Mapper<T> {

    /**
     * 将{@code ResultSet}映射成一个实体类。
     *
     * @param rs 数据库结果集
     * @param row 行数，从1开始。若从0开始，则需手动判断ResultSet是否有下一行
     * @return 转换后的实体类
     * @throws SQLException 当发生异常时抛出
     */
    T map(ResultSet rs, int row) throws SQLException;

    /**
     * 将{@code ResultSet}映射成一个实体集合
     * @param rs 数据库结果集
     * @return 转换后的实体类集合
     * @throws SQLException 当发生异常时抛出
     */
    default List<T> mapAll(ResultSet rs) throws SQLException {
        List<T> tList = new ArrayList<>();
        int row = 0;

        while (rs != null && rs.next()) {
            row ++;
            T t = map(rs, row);
            tList.add(t);
        }

        return tList;
    }
}
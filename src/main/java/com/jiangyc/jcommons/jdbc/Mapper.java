package com.jiangyc.jcommons.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code ResultSet}到实体类的映射的主接口。
 */
public interface Mapper<T> {

    /**
     * 将{@code ResultSet}映射成一个实体类。
     *
     * @param rs  数据库结果集
     * @param row 行数，从1开始
     * @param <T> 实体类型
     * @return 转换后的实体类
     * @throws SQLException 当发生异常时抛出
     */
    <T> T map(ResultSet rs, int row) throws SQLException;
}
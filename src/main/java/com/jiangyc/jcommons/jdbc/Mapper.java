package com.jiangyc.jcommons.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@code ResultSet}到实体类的映射的主接口。
 */
public interface Mapper<T> {

    /**
     * 将{@code ResultSet}映射成一个实体类。
     * @param rs 数据库结果集
     * @param <T> 实体类型
     * @return 转换后的实体类
     */
    <T> T map(ResultSet rs) throws SQLException;
}
package com.jiangyc.jcommons.jdbc;

import lombok.Data;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC操作模板类，提供了对通常的JDBC操作的封装
 */
@Data
public class JdbcTemplate {
    /** 数据源 */
    private DataSource dataSource;

    public Executor execute(String sql, Object[] args) {
        return new Executor(dataSource, sql, args);
    }

    /**
     * 执行更新操作，并获取影响的行数
     * @param sql 更新操作所用的SQL
     * @param args SQL参数
     * @return 影响的行数
     */
    public int update(String sql, Object[] args) {
        Executor executor = execute(sql, args);
        int retVal = executor.executeUpdate();
        executor.close();
        return retVal;
    }

    /**
     * 执行SQL，并返回结果
     * @param sql 查询操作所用的SQL
     * @param args SQL参数
     * @param mapper Result
     * @param <T> 结果类型
     * @return 返回的结果
     * @see Mapper#map(ResultSet, int)
     */
    public <T> T query(String sql, Object[] args, Mapper<T> mapper) {
        Executor executor = execute(sql, args);
        ResultSet resultSet = executor.executeQuery();

        try {
            T t = null;
            if (resultSet != null && resultSet.next()) {
                t = mapper.map(resultSet, 1);
            }
            return t;
        } catch (SQLException e) {
            throw new RuntimeException("读取ResultSet失败！", e);
        } finally {
            executor.close();
        }
    }

    /**
     * 执行检索语句，返回数据集合。
     * @param sql
     * @param args
     * @param mapper
     * @param <T>
     * @return
     */
    public <T> List<T> queryAsList(String sql, Object[] args, Mapper<T> mapper) {
        Executor executor = execute(sql, args);
        ResultSet resultSet = executor.executeQuery();

        try {
            int row = 1;
            List<T> tList = new ArrayList<>();
            while (resultSet != null && resultSet.next()) {
                tList.add(mapper.map(resultSet, row));
                row ++;
            }
            return tList;
        } catch (SQLException e) {
            throw new RuntimeException("读取ResultSet失败！", e);
        } finally {
            executor.close();
        }
    }
}

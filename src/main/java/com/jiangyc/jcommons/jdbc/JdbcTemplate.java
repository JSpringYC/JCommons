package com.jiangyc.jcommons.jdbc;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * JDBC操作模板类，提供了对通常的JDBC操作的封装
 * @author jiangyc
 */
public class JdbcTemplate {
    /** 数据源 */
    private DataSource dataSource;

    /**
     * 执行SQL，返回SQL执行器
     * @param sql 要执行的SQL
     * @param args SQL参数
     * @return SQL执行器
     * @see JdbcExecutor#JdbcExecutor(DataSource, String, Object[])
     */
    public JdbcExecutor execute(String sql, Object[] args) {
        return new JdbcExecutor(dataSource, sql, args);
    }

    /**
     * 执行更新操作，并获取影响的行数
     * @param sql 更新操作所用的SQL
     * @param args SQL参数
     * @return 影响的行数
     */
    public int update(String sql, Object[] args) {
        JdbcExecutor executor = new JdbcExecutor(dataSource, sql, args);
        int retVal = executor.update();
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
        JdbcExecutor executor = new JdbcExecutor(dataSource, sql, args);
        ResultSet resultSet = executor.query();

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
     * 执行SQL，并返回结果
     * @param sql 查询操作所用的SQL
     * @param args SQL参数
     * @param mapper Result
     * @param <T> 结果类型
     * @return 返回的结果
     * @see Mapper#map(ResultSet, int)
     */
    public <T> List<T> queryAll(String sql, Object[] args, Mapper<T> mapper) {
        JdbcExecutor executor = new JdbcExecutor(dataSource, sql, args);
        ResultSet rs = executor.query();

        try {
            return mapper.mapAll(rs);
        } catch (SQLException e) {
            throw new RuntimeException("读取ResultSet失败！", e);
        } finally {
            executor.close();
        }
    }

    /**
     * 执行SQL，并返回字符串结果
     * @param sql 查询操作所用的SQL
     * @param args SQL参数
     * @return 返回的结果
     */
    public String queryString(String sql, Object[] args) {
        return query(sql, args, (rs, row) -> rs.getString(1));
    }

    /**
     * 执行SQL，并返回<code>Long</code>结果
     * @param sql 查询操作所用的SQL
     * @param args SQL参数
     * @return 返回的结果
     */
    public Long queryLong(String sql, Object[] args) {
        return query(sql, args, (rs, row) -> rs.getLong(1));
    }

    /**
     * 执行SQL，并返回<code>Int</code>结果
     * @param sql 查询操作所用的SQL
     * @param args SQL参数
     * @return 返回的结果
     */
    public int queryInt(String sql, Object[] args) {
        return query(sql, args, (rs, row) -> rs.getInt(1));
    }

    /**
     * 执行SQL，并返回<code>Byte</code>结果
     * @param sql 查询操作所用的SQL
     * @param args SQL参数
     * @return 返回的结果
     */
    public Byte queryByte(String sql, Object[] args) {
        return query(sql, args, (rs, row) -> rs.getByte(1));
    }

    /**
     * 执行SQL，并返回<code>Short</code>结果
     * @param sql 查询操作所用的SQL
     * @param args SQL参数
     * @return 返回的结果
     */
    public Short queryShort(String sql, Object[] args) {
        return query(sql, args, (rs, row) -> rs.getShort(1));
    }
}

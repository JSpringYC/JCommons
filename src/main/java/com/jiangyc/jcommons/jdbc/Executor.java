package com.jiangyc.jcommons.jdbc;

import lombok.Data;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库执行器类
 */
@Data
public class Executor {
    /** 数据源 */
    private DataSource dataSource;
    /** 数据库连接 */
    private Connection connection;
    /** Statement */
    private PreparedStatement statement;
    /** SQL语句 */
    private String sql;
    /** SQL语句参数 */
    private Object[] args;
    /** 结果集 */
    private ResultSet resultSet;

    public Executor(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
    }

    public Executor(DataSource dataSource, String sql, Object[] args) {
        this.dataSource = dataSource;
        prepareStatement(sql, args);
    }

    public void prepareStatement(String sql, Object[] args) {
        try {
            this.connection = dataSource.getConnection();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException("获取数据库连接失败：" + dataSource, e);
        }

        try {
            this.statement = connection.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    statement.setObject(i + 1, args[i]);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("准备SQL语句失败：sql=" + sql + ", args=" + args, e);
        }
    }

    /**
     * 执行更新操作，获取影响行数，并关闭连接
     * @return 执行结果
     */
    public int executeUpdate() {
        try {
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("执行更新操作时失败：sql=" + sql + ", args=" + args, e);
        } finally {
            close();
        }
    }

    public ResultSet executeQuery() {
        try {
            this.resultSet = statement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException("执行查询操作时失败：sql=" + sql + ", args=" + args, e);
        }
    }

    /**
     * 关闭连接，并清空数据
     */
    public void close() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException("关闭ResultSet失败", e);
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException("关闭Statement失败", e);
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("关闭Connection失败", e);
            }
        }
    }
}

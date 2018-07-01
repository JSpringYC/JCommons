package com.jiangyc.jcommons.jdbc;

import com.jiangyc.jcommons.io.Closeables;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JDBC执行器
 *
 * @author jiangyc
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class JdbcExecutor {
    /**
     * JDBC数据源
     */
    @NonNull
    private DataSource dataSource;

    /**
     * JDBC连接
     */
    private Connection connection;

    /**
     * prepared statement
     */
    private PreparedStatement statement;

    /**
     * JDBC结果集
     */
    private ResultSet resultSet;

    /**
     * SQL
     */
    private String sql;

    /**
     * 参数
     */
    private Object[] args;

    /**
     * 创建JDBC执行器，并准备SQL
     * @param dataSource 数据源
     * @param sql 要执行的SQL
     * @param args SQL参数
     */
    public JdbcExecutor(DataSource dataSource, String sql, Object[] args) {
        this(dataSource);

        prepareStatement(sql, args);
    }

    /**
     * 准备SQL，并设置参数
     * @param sql 要执行的SQL
     * @param args SQL参数
     */
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
    public int update() {
        try {
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("执行更新操作时失败!" + sql + ", args=" + args, e);
        } finally {
            close();
        }
    }

    /**
     * 执行查询操作
     * @return 执行结果集
     */
    public ResultSet query() {
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

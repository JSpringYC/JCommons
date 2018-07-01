package com.jiangyc.jcommons.jdbc.crm;

import com.jiangyc.jcommons.jdbc.JdbcTemplate;
import com.jiangyc.jcommons.jdbc.Mapper;

import java.sql.ResultSet;
import java.util.List;

/**
 * CRM操作模板，基于<code>JdbcTemplate</code>
 * @author jiangyc
 */
public class CrmTemplate extends JdbcTemplate {

    /**
     * 执行SQL，并返回结果
     * @param sql 查询操作所用的SQL
     * @param args SQL参数
     * @param cls 结果类型
     * @param <T> 结果类型
     * @return 返回的结果
     * @see Mapper#map(ResultSet, int)
     */
    public <T> T query(String sql, Object[] args, Class<T> cls) {
        Mapper<T> mapper = MapperFactory.getMapper(cls);

        return query(sql, args, mapper);
    }

    /**
     * 执行SQL，并返回结果
     * @param sql 查询操作所用的SQL
     * @param args SQL参数
     * @param cls 结果类型
     * @param <T> 结果类型
     * @return 返回的结果
     * @see Mapper#map(ResultSet, int)
     */
    public <T> List<T> queryAll(String sql, Object[] args, Class<T> cls) {
        Mapper<T> mapper = MapperFactory.getMapper(cls);

        return queryAll(sql, args, mapper);
    }
}

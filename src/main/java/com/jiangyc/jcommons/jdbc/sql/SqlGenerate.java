package com.jiangyc.jcommons.jdbc.sql;

import com.jiangyc.jcommons.jdbc.bean.Table;
import lombok.Data;

/**
 * SQL生成器接口，根据<code>Table</code>实体类来生成增删改查的SQL
 *
 * @author jiangyc
 */
public interface SqlGenerate {

    /**
     * 根据给定的映射信息生成实例
     *
     * @param table 要生成实例所使用的映射信息
     * @return 生成的SQL
     */
    String generate(Table table);
}

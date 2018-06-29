package com.jiangyc.jcommons.jdbc;

import java.util.List;

/**
 * 基本的CRUD接口
 *
 * @author jiangyc
 */
public interface CommonDao<T> {

    /**
     * 向数据库中插入一条数据
     *
     * @param t 要插入到数据库中的数据
     */
    void insert(T t);

    /**
     * 从数据库中删除一条数据
     * @param t 要从数据库中删除的数据
     */
    void delete(T t);

    /**
     * 更新数据库中的数据
     * @param t 要更新的记录
     */
    void update(T t);

    /**
     * 根据给定记录对应的表的主键来判断向数据库中插入或更新数据
     * @param t 要插入或更新的记录
     */
    void insertOrUpdate(T t);

    /**
     * 获取总记录数
     * @return 总记录数
     */
    long count();

    /**
     * 根据ID来查找记录
     * @param ids 要查找的记录的ID
     * @return 符合给定要求的记录
     */
    T findById(Object[] ids);

    /**
     * 查找所有记录
     * @return 所有的记录
     */
    List<T> findAll();

    /**
     * 分页查找所有的记录
     * @param offset 分页开始位置
     * @param rows 分页长度
     * @return 所有的记录
     */
    List<T> findAll(int offset, int rows);

    // ***********************************************
    //  SQL相关
    // ***********************************************

    /**
     * 执行原生SQL
     * @param sql 要执行的SQL
     * @param args SQL中使用的参数
     */
    void execute(String sql, Object[] args);

    /**
     * 查询一条记录
     * @param sql 要执行的SQL
     * @param cls 目标类的类型
     * @param args SQL中使用的参数
     * @return 符合目标的一条记录
     */
    T query(String sql, Class<T> cls, Object[] args);

    /**
     * 查询所有记录
     * @param sql 要执行的SQL
     * @param cls 目标类的类型
     * @param args SQL中使用的参数
     * @return 符合目标的一条记录
     */
    List<T> queryAll(String sql, Class<T> cls, Object[] args);
}

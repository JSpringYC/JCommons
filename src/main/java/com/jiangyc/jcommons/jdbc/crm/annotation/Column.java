package com.jiangyc.jcommons.jdbc.crm.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解被用来注解一个同时被<code>@Table</code>注解的POJO的某个属性，表示该类的此属性和这张表的列相关联。
 *
 * <p>注意：只有被此属性注解，才会被与数据库列相关联。
 *
 * @author jiangyc
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {

    /**
     * 被注解的属性对应的数据库表的列的列名。当该值为空时，则默认使用被注解的属性的属性名小写作为列名。
     *
     * @return 字符串，表示被注解的属性对应的数据库表的列的列名
     */
    String value() default "";

    /**
     * 表示该属性对应的列是否是主键
     * @return 布尔值，表示该属性对应的列是否是主键
     */
    boolean primary() default false;

    /**
     * 表示该属性对应的列是否非空
     * @return 布尔值，表示该属性对应的列是否非空
     */
    boolean notNull() default false;

    /**
     * 表示该属性对应的列是否可重复
     * @return 布尔值，表示该属性对应的列是否可重复
     */
    boolean unique() default false;

    /**
     * 表示该属性对应的列的最大长度，只有当被注解的属性是<code>String</code>时，该属性才会生效
     * @return 表示该属性对应的列的最大长度
     */
    int size() default 255;
}

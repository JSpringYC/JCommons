package com.jiangyc.jcommons.jdbc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 当某个POJO类被此注解标注时，标示此POJO是与数据库中某张表相关联的类，其关联信息可以在此注解中找到。
 *
 * <p>注意：虽然被注解的POJO表示与数据库表相关联，但其默认仍不会将该类的属性作为数据库表的列来处理。也就是说，如果你想同时标示数据库列时，
 * 请使用<code>@Column</code>注解.
 *
 * @author jiangyc
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {

    /**
     * 被注解的类对应的数据库表的表名。当该值为空时，则默认使用被注解的类的类名小写作为表名。
     *
     * @return 字符串，表示被注解的类对应的数据库表的表名
     */
    String value() default "";
}
package com.jiangyc.jcommons.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 一些通用的方法，如参数判断、对象复制等
 * @since 1.0
 */
public class Objects {

    /**
     * 将简单类型数组包装成复合类型数组
     * @param cs 要包装的简单类型数组
     * @return 包装成的复合类型数组
     */
    public static Character[] transfor(char[] cs) {
        nonNull(cs);

        Character[] characters = new Character[cs.length];

        for (int i = 0; i < cs.length; i++) {
            characters[i] = cs[i];
        }

        return characters;
    }

    /**
     * 将复合类型数组包装成简单类型数组
     * @param chars 要包装的复合类型数组
     * @return 包装成的简单类型数组
     */
    public static char[] transfor(Character[] chars, char def) {
        nonNull(chars);

        char[] cs = new char[chars.length];

        for (int i = 0; i < chars.length; i++) {
            cs[i] = chars[i] == null ? def : chars[i];
        }

        return cs;
    }

    /**
     * 将简单类型数组包装成复合类型数组
     * @param is 要包装的简单类型数组
     * @return 包装成的复合类型数组
     */
    public static Integer[] transfor(int[] is) {
        nonNull(is);

        Integer[] ints = new Integer[is.length];

        for (int i = 0; i < is.length; i++) {
            ints[i] = is[i];
        }

        return ints;
    }

    /**
     * 将简单类型数组包装成复合类型数组
     * @param bs 要包装的简单类型数组
     * @return 包装成的复合类型数组
     */
    public static Byte[] transfor(byte[] bs) {
        nonNull(bs);

        Byte[] bytes = new Byte[bs.length];

        for (int i = 0; i < bs.length; i++) {
            bytes[i] = bs[i];
        }

        return bytes;
    }

    /**
     * 将数组转换为集合
     * @param es 要转换的数组
     * @param <E> 数组内的类型
     * @return 转换的集合
     */
    public static <E> List<E> asList(E[] es) {
        nonNull(es);

        List<E> eList = new ArrayList<>();

        for (E e : es) {
            eList.add(e);
        }

        return eList;
    }

    /**
     * 判断给定的参数是否为{@code null}
     * @param o 要判断的参数
     * @return 参数是否为{@code null}
     */
    public static boolean isNull(Object o) {
        return o == null;
    }

    /**
     * 判断给定的参数是否为{@code null}
     * @param o 要判断的参数
     * @return 参数是否为{@code null}
     */
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }

        if (o instanceof String) {
            return ((String) o).length() <= 0;
        }

        if (o instanceof Iterable) {
            return ((Iterable) o).iterator().hasNext();
        }

        if (o instanceof Map) {
            return ((Map) o).isEmpty();
        }

        return false;
    }

    /**
     * 确保参数不为空。当参数为空时，将抛出异常{@code IllegalArgumentException}
     * @param t 要判断的参数
     * @param <T> 参数的类型
     * @return 不为空的参数
     */
    public static <T> T nonNull(T t) {
        if (t == null) {
            throw new IllegalArgumentException("argument can not be null!");
        }
        return t;
    }

    /**
     * 确保参数不为空。当参数为空时，将抛出异常。
     * @param t 要判断的参数
     * @param message 当抛出异常时，使用的异常信息
     * @param <T> 参数类型
     * @return 不为空的参数
     */
    public static <T> T nonNull(T t, String message) {
        if (t == null) {
            throw new IllegalArgumentException(message);
        }
        return t;
    }

    /**
     * 变量克隆，即创造一个与原本变量类型一致、值也一致的不同对象。
     * @param t 要克隆的变量
     * @param <T> 变量类型
     * @return 克隆出的变量
     */
    public static <T> T clone(T t) {
        // 过滤基本的数字类型
        if (t instanceof Number) {
            return t;
        }

        // 参数不为空
        nonNull(t);

        try {
            Object o = t.getClass().newInstance();
            BeanUtils.copyProperties(t, o);
            return (T) o;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 变量克隆，创造一个指定类型的、值一致的不同对象。
     * @param t 要克隆的变量
     * @param c 要克隆成的类型的Class
     * @param <T> 变量类型
     * @param <V> 要克隆成的类型
     * @return 克隆出的变量
     */
    public static <T, V> V clone(T t, Class<V> c) {
        c = nonNull(c);

        try {
            V v = c.newInstance();
            BeanUtils.copyProperties(t, v);

            return v;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 变量克隆，创造一个指定类型的、值一致的不同对象。
     * @param ts 要克隆的变量
     * @param c 要克隆成的类型的Class
     * @param <T> 变量类型
     * @param <V> 要克隆成的类型
     * @return 克隆出的变量
     */
    public static <T, V> Collection<V> clone(Collection<T> ts, Class<V> c) {
        ts = nonNull(ts);
        c = nonNull(c);

        Collection<V> vs = new ArrayList<V>();

        try {
            for (T t : ts) {
                V v = c.newInstance();
                BeanUtils.copyProperties(t, v);
                vs.add(v);
            }

            return vs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

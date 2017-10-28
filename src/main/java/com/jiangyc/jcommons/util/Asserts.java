package com.jiangyc.jcommons.util;

import java.io.File;

/**
 * 一系列断言的静态方法
 */
public class Asserts {

    /**
     * 参数不能为空
     * @param o 要断言的对象
     */
    public static void notNull(Object o) {
        notNull(o, "参数不能为空!");
    }

    /**
     * 参数不能为空
     * @param o 要断言的对象
     * @param message 提示信息
     */
    public static void notNull(Object o, String message) {
        if (o == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 文件存在
     * @param file 要断言的文件
     */
    public static void fileExist(File file) {
        fileExist(file, "文件不存在!");
    }

    /**
     * 文件存在
     * @param file 要断言的文件
     * @param message 提示信息
     */
    public static void fileExist(File file, String message) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException(message);
        }
    }
}

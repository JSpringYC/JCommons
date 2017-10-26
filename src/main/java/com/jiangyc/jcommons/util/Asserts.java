package com.jiangyc.jcommons.util;

public class Asserts {

    public static void notNull(Object o) {
        notNull(o, "参数不能为空!");
    }

    public static void notNull(Object o, String message) {
        if (o == null) {
            throw new IllegalArgumentException(message);
        }
    }
}

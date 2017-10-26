package com.jiangyc.jcommons.io;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 关闭一个可关闭对象<code>Closeable</code>的工具类
 */
public class Closeables {

    /**
     * 关闭一个或多个对象
     * @param cs 要关闭的对象
     * @return 关闭对象发生的异常
     */
    public static List<IOException> close(Closeable... cs) {
        // 将要抛出的异常。
        List<IOException> exceptions = null;

        // 逐个关闭各个对象，如果出错，将其压入list中，待遍历完后抛出
        for (Closeable c : cs) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException e) {
                    // 空判断
                    exceptions = (exceptions == null) ? new ArrayList<>() : exceptions;
                    exceptions.add(e);
                }
            }
        }

        // 检查是否发生异常，如果发生，将其抛出
        if (exceptions != null && exceptions.size() > 0) {
            return exceptions;
        }
        // 如果未发生异常，返回null
        return null;
    }

    /**
     * 静默的关闭对象
     * @param cs 要关闭的对象
     */
    public static void closeQuietly(Closeable... cs) {
        // 逐个关闭各个对象
        for (Closeable c : cs) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException e) {
                    // 忽略异常
                }
            }
        }
    }
}

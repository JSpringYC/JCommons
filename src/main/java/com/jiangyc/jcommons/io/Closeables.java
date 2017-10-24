package com.jiangyc.jcommons.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * 关闭一个可关闭对象<code>Closeable</code>的工具类
 */
public class Closeables {

    /**
     * 关闭一个或多个对象，
     * @param cs
     * @throws IOException
     */
    public static void close(Closeable... cs) throws IOException {
        // 将要抛出的异常。
        IOException ioEx = null;

        // 逐个关闭各个对象，如果出错，将其压入ioEx中，待遍历完后抛出
        for (Closeable c : cs) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException e) {
                    if (ioEx == null) {
                        ioEx = e;
                    } else {
                        ioEx.addSuppressed(e);
                    }
                }
            }
        }

        // 检查是否发生异常，如果发生，将其抛出
        if (ioEx != null) {
            throw ioEx;
        }
    }
}

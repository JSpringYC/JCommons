package com.jiangyc.jcommons.io;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 关闭一个可关闭对象<code>Closeable</code>的工具类.
 *
 * 在IO或JDBC操作时，常要显示的关闭对象，并处理可能要抛出的异常。在大部分情况下，我们在关闭该对象时，需要先判空，然后在关闭该对象时捕获
 * 可能抛出的异常。然而我们通常不会处理该异常，其代码可能如下：
 * <pre>
 *     InputStream input = null;
 *
 *     try {
 *         // IO操作
 *     } catch(IOException e) {
 *         // 异常处理
 *     } finally {
 *         if(input != null) {
 *             try {
 *                 input.close();
 *             } catch(IOException e) {
 *             }
 *         }
 *     }
 *
 * 该类就是为了解决以上的繁琐的代码。如<code>close()</code>方法关闭一个或多个可关闭的对象，并在发生异常时，将异常转换为
 * <code>RuntimeException</code>，在调用此方法时，可根据自己的需要决定是否捕获此异常。<code>closeQuietly()</code>方法异常关闭所有
 * 的可关闭对象，并在发生异常时屏蔽该异常，最终不需要抛出异常。
 * </pre>
 */
public class Closeables {

    /**
     * 关闭一个或多个可关闭对象.
     *
     * 发生异常时，会将异常转换为为<code>RuntimeException</code>，并将关闭其它流时抛出的异常以<code>addSuppressed()</code>的方法
     * 添加到第一个异常中。
     *
     * @param cs 要关闭的对象
     */
    public static void close(Closeable... cs) {
        RuntimeException rtEx = null;

        // 逐个关闭各个对象，如果出错，将其压入list中，待遍历完后抛出
        for (Closeable c : cs) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException e) {
                    rtEx = (rtEx == null) ? new RuntimeException() : rtEx;
                    rtEx.addSuppressed(e);
                }
            }
        }

        if (rtEx != null) {
            throw rtEx;
        }
    }

    /**
     * 静默的关闭对象
     *
     * 该方法会屏蔽可能发生的异常
     *
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

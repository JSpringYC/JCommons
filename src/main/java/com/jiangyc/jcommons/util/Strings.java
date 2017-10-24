package com.jiangyc.jcommons.util;

/**
 * 字符串工具类，包含了操作字符串的一系列静态方法。
 *
 * @since 1.0
 */
public class Strings {

    /**
     * 文件分隔符。在Windows上，为<code>\</code>，在*Nix上，为<code>/</code>
     */
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    /**
     * 换行分隔符。
     */
    public static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * 判断给定的参数是否为空(null或"")
     * @param s 要检查的参数
     * @return 给定的参数是否为空
     */
    public boolean isEmpty(String s) {
        return (s == null) ? true : s.length() == 0;
    }

    /**
     * 判断给定的参数是否为空或过滤过空白字符后是否为空
     * @param s
     * @return
     */
    public boolean isTrimEmpty(String s) {
        return (s == null) ? true : s.trim().length() == 0;
    }

    /**
     * 获取字符的长度
     * @param s 要判断的字符
     * @return 字符的长度，空字符将被看作长度为0
     */
    public int length(String s) {
        return (isEmpty(s)) ? 0 : s.length();
    }

    /**
     * 获取经过过滤空白字符后的长度
     * @param s 要检查的长度
     * @return 字符的长度，空白字符将被看作长度为0
     */
    public int trimLength(String s) {
        return (isTrimEmpty(s)) ? 0 : s.trim().length();
    }
}

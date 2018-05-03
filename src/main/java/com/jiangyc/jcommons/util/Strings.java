package com.jiangyc.jcommons.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public static boolean isEmpty(String s) {
        return (s == null) ? true : s.length() == 0;
    }

    /**
     * 判断给定的参数是否为空或过滤过空白字符后是否为空
     * @param s 字符串
     * @return 字符串是否为空
     */
    public static boolean isBlank(String s) {
        return (s == null) ? true : s.trim().length() == 0;
    }

    /**
     * 获取字符的长度
     * @param s 要判断的字符
     * @return 字符的长度，空字符将被看作长度为0
     */
    public static int length(String s) {
        return (isEmpty(s)) ? 0 : s.length();
    }

    /**
     * 获取经过过滤空白字符后的长度
     * @param s 要检查的长度
     * @return 字符的长度，空白字符将被看作长度为0
     */
    public static int trimLength(String s) {
        return (isBlank(s)) ? 0 : s.trim().length();
    }

    /**
     * 获取字符Stream
     * @param s 要转换的字符串
     * @return {@code java.util.stream.Stream<String>}
     */
    public static Stream<Character> toCharStream(String s) {
        return Objects.asList(Objects.transfor(s.toCharArray())).stream();
    }

    /**
     * 字符串Join操作。默认不会跳过空白字符
     * @param stream 要操作的字符串数组Stream
     * @param separator 分隔符
     * @return Join操作后的字符串
     */
    public static String join(Stream<String> stream, String separator) {
        Objects.nonNull(stream);
        Objects.nonNull(separator);

        List<String> strings = stream.collect(Collectors.toList());
        int size = strings.size();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < size; i++) {
            sb.append(strings.get(i));
            if (!(i == size-1)) {
                sb.append(separator);
            }
        }

        return sb.toString();
    }

    /**
     * 获取字符串Stream
     * @param s 字符串源
     * @return 字符串Stream
     */
    public static Stream<String> toStream(String s) {
        return Arrays.asList(s).stream();
    }

    /**
     * 获取字符串Stream
     * @param ss 字符串源
     * @return 字符串Stream
     */
    public static Stream<String> toStream(String[] ss) {
        ss = Objects.nonNull(ss);

        return Objects.asList(ss).stream();
    }
}

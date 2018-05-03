/*
 * JCommons
 * Copyright (C) 2018 姜永春
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jiangyc.jcommons.util;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Unicode操作工具类
 */
public class Unicodes {

    /**
     * 将一段字符串转换为Unicode格式
     * @param s 要转换的字符串，不能为空
     * @return Unicode格式的字符串
     */
    public static String encode(String s) {
        return s.chars().mapToObj((i) -> {
            String temp = Integer.toHexString(i);
            temp = (temp .length() > 2) ? temp : "00" + temp;
            return temp = "\\u" + temp;
        }).collect(Collectors.joining());
    }

    /**
     * 将Unicode格式的字符串转换普通字符串
     * @param s 要转换的Unicode格式的字符串，不能为空
     * @return 转换后的普通字符串
     */
    public static String decode(String s) {
        String[] strings = s.toLowerCase().split("\\\\u");

        return Arrays.stream(strings).filter((temp) -> !temp.isEmpty()).map(
            temp -> Character.toString((char) Integer.parseInt(temp, 16))
        ).collect(Collectors.joining());
    }
}

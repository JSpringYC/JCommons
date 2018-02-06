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

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateFormatsTest {
    private static final String sdf = "yyyy-MM-dd HH:mm:ss sss";

    @Test
    public void testMills() {
        Map<String, String> map = new HashMap<>();
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");
        map.put("five", "5");
        map.put("six", "6");
        map.put("fe", "6");
        map.put("sfeix", "6");
        map.put("sfeix", "6");
        map.put("sefix", "6");
        map.put("sfeaix", "6");
        map.put("fa", "6");
        map.put("sfeaix", "6");
        map.put("sifeafx", "6");
        String format = "yyyy-MM-dd";

        long mills1 = System.currentTimeMillis();
        SimpleDateFormat sdformat = new SimpleDateFormat(format);
        long mills2 = System.currentTimeMillis();
        boolean b = map.containsKey("abc");
        long mills3 = System.currentTimeMillis();

        System.out.println("New: " + (mills2 - mills1));
        System.out.println("Map: " + (mills3 - mills2));
    }

    @Test
    public void format1() {
        System.out.println(DateFormats.format(new Date(), sdf));
    }

    @Test
    public void format3() {
        System.out.println(DateFormats.format(System.currentTimeMillis(), sdf));
    }

    @Test
    public void format5() {
        System.out.println(DateFormats.format(Calendar.getInstance(), sdf));
    }
}
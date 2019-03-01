package com.jiangyc.jcommons.util;

import java.util.Date;

/**
 * 身份证相关操作的工具类
 *
 * @author jiangyc
 */
public abstract class Idcard {

    // *** 生成 ***

    /**
     * 根据给定的相关信息，生成一个身份证号码
     *
     * @param region 6位的辖区编码，如010101
     * @param birthday 8位的出生日期，格式为：yyyyMMdd
     * @param gender 性别相关的数字，0~9，奇数为男性，偶数为女性
     * @return 身份证号码
     */
    public static String generate(String region, Date birthday, int gender) {
        return generate(region, birthday, null, gender);
    }

    /**
     * 根据给定的相关信息，生成一个身份证号码
     *
     * @param region 6位的辖区编码，如010101
     * @param birthday 8位的出生日期，格式为：yyyyMMdd
     * @param ramdom 两位的随机数
     * @param gender 性别相关的数字，0~9，奇数为男性，偶数为女性
     * @return 身份证号码
     */
    public static String generate(String region, Date birthday, String ramdom, int gender) {
        throw new UnsupportedOperationException();
    }

    /**
     * 根据给定的身份证的前17位，生成第18位
     *
     * @param idcard 身份证的前17位
     * @return 身份证的第18位
     */
    public static String generateLastNumber(String idcard) {
        throw new UnsupportedOperationException();
    }

    // *** 验证 ***

    /**
     * 验证给定的身份证号码是否有效
     *
     * @param idcard 要验证的身份证号码
     * @return 给定的身份证号码是否有效
     */
    public abstract boolean isValid(String idcard);

    /**
     * 获取身份证相关的辖区编码，如果辖区编码不正确，则返回<code>null</code>.
     *
     * @param idcard 身份证号
     * @return 辖区编码
     */
    public abstract String getRegion(String idcard);

    /**
     * 获取身份证相关的出生日期，如果出生日期不正确，则返回<code>null</code>.
     *
     * @param idcard 身份证号
     * @return 出生日期
     */
    public abstract Date getBirthday(String idcard);

    /**
     * 判断给定的身份证号关联的人是否为男性
     *
     * @param idcard 要判断的身份证号
     * @return 身份证号关联的人是否为男性
     */
    public static boolean isFemale(String idcard) {
        // 判断身份证格式
        if (Strings.length(idcard) != 18) {
            return false;
        }
        // 获取身份证中的性别数字
        try {
            int gender = Integer.parseInt(idcard.substring(16, 17));

            return gender % 2 == 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断给定的身份证号关联的人是否为女性
     *
     * @param idcard 要判断的身份证号
     * @return 身份证号关联的人是否为女性
     */
    public static boolean isMale(String idcard) {
        // 判断身份证格式
        if (Strings.length(idcard) != 18) {
            return false;
        }
        // 获取身份证中的性别数字
        try {
            int gender = Integer.parseInt(idcard.substring(16, 17));

            return gender % 2 == 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

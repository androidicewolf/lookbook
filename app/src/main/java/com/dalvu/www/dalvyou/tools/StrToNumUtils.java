package com.dalvu.www.dalvyou.tools;

import java.math.BigDecimal;

/**
 * 字符串转换成两位浮点型的工具类
 * Created by user on 2017/6/29.
 */

public class StrToNumUtils {
    /**
     * 将String型转换成double型
     *
     * @param s 想要转换的字符串
     * @return 返回一个保留两位的double型
     */
    public static double str2Double(String s) {
        return new BigDecimal(s).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 将String型转换成有两位小数点的字符串
     *
     * @param s 想要转换的字符串
     * @return 返回一个保留两位的String型
     */
    public static String str2Str(String s) {
        return String.format("%.2f", Double.valueOf(s) / 100);
    }
}

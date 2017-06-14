package com.dalvu.www.dalvyou.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证输入内容格式的工具类
 * Created by user on 2017/6/14.
 */

public class NumberUtils {

    /**
     * 验证手机号
     *
     * @param number 输入手机号
     * @return boolean
     */
    public static boolean isMobileNo(String number) {
        Pattern p = Pattern.compile("^((\\+86)|(86))?[1][3456789][0-9]{9}$");
        Matcher m = p.matcher(number);
        return m.matches();
    }

    /**
     * 验证邮箱
     *
     * @param email 输入邮箱
     * @return boolean
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 验证密码
     *
     * @param password 输入的密码
     * @return boolean
     */
    public static boolean isPassword(String password) {
        String str = "[a-zA-Z0-9_]{6,20}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(password);
        return m.matches();
    }
}

package com.dalvu.www.dalvyou.tools;

/**
 * 在内存中保存用户信息的类
 * Created by user on 2017/5/24.
 */

public class AppUserDate {
    private static int userType = 0;

    public static int getUserType() {
        return userType;
    }

    public static void setUserType(int type) {
        userType = type;
    }
}

package com.dalvu.www.dalvyou.tools;

/**
 * 在内存中保存用户信息的类
 * Created by user on 2017/5/24.
 */

public class AppUserDate {
    private static int userType = 0;
    private static int userId = 0;
    private static String userToken = "";

    public static int getUserType() {
        return userType;
    }

    public synchronized static void setUserType(int type) {
        userType = type;
    }

    public static int getUserId() {
        return userId;
    }

    public synchronized static void setUserId(int id) {
        userId = id;
    }

    public static String getUserToken() {
        return userToken;
    }

    public synchronized static void setUserToken(String token) {
        userToken = token;
    }
}

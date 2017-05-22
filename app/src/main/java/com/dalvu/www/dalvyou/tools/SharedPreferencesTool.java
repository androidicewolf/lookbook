package com.dalvu.www.dalvyou.tools;

import android.content.Context;
import android.content.SharedPreferences;

/**sp本地文件存储的工具类
 * Created by user on 2017/5/9.
 */

public class SharedPreferencesTool {
    private static SharedPreferences sp;

    /**
     * 保存boolean值信息
     *
     */
    public static void saveBoolean(Context context, String key, boolean value){
        if (sp==null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * 获取boolean值信息
     *
     */
    public static boolean getBoolean(Context context,String key,boolean defvalue){
        if (sp==null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defvalue);
    }
}

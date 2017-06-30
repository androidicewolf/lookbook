package com.dalvu.www.dalvyou;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.dalvu.www.dalvyou.tools.CustomValue;
import com.dalvu.www.dalvyou.tools.NullStringToEmptyAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DBCacheStore;
import com.yanzhenjie.nohttp.rest.RequestQueue;

import java.util.LinkedList;
import java.util.List;

/**一个钢镚儿
 * Created by user on 2017/5/8.
 */

public class MyApplication extends Application {
    private static RequestQueue requestQueue;
    private static MyApplication myApplication;
    private static Gson gson;
    //关闭activity的方法
    private List<Activity> mList = new LinkedList<>();

    public static RequestQueue getRequestQueue(){
        return requestQueue;
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }

    public static Gson getGson() {
        return gson;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        NoHttp.initialize(this, new NoHttp.Config()
        .setNetworkExecutor(new OkHttpNetworkExecutor())
                .setCacheStore(new DBCacheStore(this).setEnable(true)));
        requestQueue = NoHttp.newRequestQueue();

        //将Gson初始化出来，变成全局对象使用
        gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();

        CustomValue.CITYS.add("北京市");
        CustomValue.CITYS.add("天津市");
        CustomValue.CITYS.add("河北省");
        CustomValue.CITYS.add("唐山市");
    }

    public int[] getScreenSize() {
        int[] screenSize = new int[2];
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        screenSize[0] = displayMetrics.widthPixels;
        screenSize[1] = displayMetrics.heightPixels;
        return screenSize;
    }

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public void closeActivity() {
        for (Activity activity : mList) {
            activity.finish();
        }
    }
}

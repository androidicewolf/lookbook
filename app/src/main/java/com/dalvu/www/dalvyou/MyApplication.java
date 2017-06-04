package com.dalvu.www.dalvyou;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DBCacheStore;
import com.yanzhenjie.nohttp.rest.RequestQueue;

/**一个钢镚儿
 * Created by user on 2017/5/8.
 */

public class MyApplication extends Application {
    private static RequestQueue requestQueue;
    private static MyApplication myApplication;
    public static RequestQueue getRequestQueue(){
        return requestQueue;
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        NoHttp.initialize(this, new NoHttp.Config()
        .setNetworkExecutor(new OkHttpNetworkExecutor())
        .setConnectTimeout(15000)
        .setReadTimeout(15000).setCacheStore(new DBCacheStore(this).setEnable(true)));
        requestQueue = NoHttp.newRequestQueue();
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
}

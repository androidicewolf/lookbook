package com.dalvu.www.dalvyou;

import android.app.Application;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DBCacheStore;
import com.yanzhenjie.nohttp.cache.DiskCacheStore;
import com.yanzhenjie.nohttp.rest.RequestQueue;

/**一个钢镚儿
 * Created by user on 2017/5/8.
 */

public class MyApplication extends Application {
    private static RequestQueue requestQueue;
    public static RequestQueue getRequestQueue(){
        return requestQueue;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this, new NoHttp.Config()
        .setNetworkExecutor(new OkHttpNetworkExecutor())
        .setConnectTimeout(15000)
        .setReadTimeout(15000).setCacheStore(new DBCacheStore(this).setEnable(true)));
        requestQueue = NoHttp.newRequestQueue();
    }
}

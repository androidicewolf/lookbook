package com.dalvu.www.dalvyou.netUtils;

import com.dalvu.www.dalvyou.MyApplication;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.Map;

/**请求网络的工具类
 * Created by user on 2017/5/8.
 */

public class NetUtils {
    /**请求网络，不需要在界面上展示**/
    public static void callNet(int what, String uri, final MyCallBack callBack){
        final Request<String> request = NoHttp.createStringRequest(uri, RequestMethod.POST);

        MyApplication.getRequestQueue().add(what, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
                callBack.onStart(what);
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                if (response.responseCode() == 200) {
                    callBack.onSucceed(what, response.get());
                }else{
                    callBack.onFailed(what, response.responseCode());
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                callBack.onFailed(what, -1);
            }

            @Override
            public void onFinish(int what) {
                callBack.onFinish(what);
            }
        });
    }
    /**请求网络,用户登录**/
    public static void callNet(int what, String uri, Map<String, Integer> map, final MyCallBack callBack) {
        final Request<String> request = NoHttp.createStringRequest(uri, RequestMethod.POST);
        for (String key : map.keySet()){
            request.add(key, map.get(key));
        }
        MyApplication.getRequestQueue().add(what, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
                callBack.onStart(what);
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                if (response.responseCode() == 200) {
                    callBack.onSucceed(what, response.get());
                }else{
                    callBack.onFailed(what, response.responseCode());
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                callBack.onFailed(what, -1);
            }

            @Override
            public void onFinish(int what) {
                callBack.onFinish(what);
            }
        });
    }
    /**请求网络,展示数据，带有网络状态的界面**/
}

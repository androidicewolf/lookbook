package com.dalvu.www.dalvyou.netUtils;

/**自定义网络请求回调借口
 * Created by user on 2017/5/9.
 */

public class MyCallBack {
    private StateView stateView;

    /**
     * 无参构造，使用时必须重写四种方法
     */
    public MyCallBack() {
    }
    public MyCallBack(StateView stateView){
        this.stateView = stateView;
    }

    public void onStart(int what) {
        stateView.showLoading();
    }

    public void onSucceed(int what, String json) {
    }

    /**请求服务器链接失败返回code为-1**/
    public void onFailed(int what, int code) {
        stateView.showError();
    }

    public void onFinish(int what) {
    }
}

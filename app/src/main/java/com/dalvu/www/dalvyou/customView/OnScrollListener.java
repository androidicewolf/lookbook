package com.dalvu.www.dalvyou.customView;

/**
 * 自定义的接口，用于自定义ListenedScrollView的监听回调
 * Created by wolf on 17.6.4.
 */

public interface OnScrollListener {
    /**
     *  * 滑动到底部回调
     *  
     */
    public void onBottomArrived();

    /**
     *  * 滑动位置回调
     *  * @param l
     *  * @param t
     *  * @param oldl
     *  * @param oldt
     *  
     */
    public void onScrollChanged(int x, int y, int oldX, int oldY);
}

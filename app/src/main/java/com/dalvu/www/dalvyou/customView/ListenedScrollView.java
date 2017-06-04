package com.dalvu.www.dalvyou.customView;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 自定义ScrollView，带有滑动监听事件
 * Created by wolf on 17.6.4.
 */

public class ListenedScrollView extends ScrollView {

    // 检查ScrollView的最终状态
    private static final int CHECK_STATE = 0;
    //是否处于触摸状态
    private boolean isTouch = false;
    //上次滑动的最后位置
    private int lastY = 0;
    //外部的监听方法
    private OnScrollListener onScrollListener;
    private Handler checkStateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (lastY == getScrollY()) {
                // 如果上次的位置和当前的位置相同，可认为是在空闲状态
                if (getScrollY() + getHeight() >= computeVerticalScrollRange()) {
                    onScrollListener.onBottomArrived();
                }
            }
        }
    };

    public ListenedScrollView(Context context) {
        super(context);
    }

    public ListenedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListenedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                isTouch = true;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                isTouch = false;
                lastY = getScrollY();
                checkStateHandler.removeMessages(CHECK_STATE);// 确保只在最后一次做这个check
                checkStateHandler.sendEmptyMessageDelayed(CHECK_STATE, 5);// 5毫秒检查一下
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldX, int oldY) {
        super.onScrollChanged(x, y, oldX, oldY);
        if (onScrollListener == null) {
            return;
        }

        if (!isTouch) {
            if (y != oldY) {
                // 没有手指触摸，并且位置有滚动，就可以简单的认为是在fling
                // 记住上次滑动的最后位置
                lastY = y;
                checkStateHandler.removeMessages(CHECK_STATE);// 确保只在最后一次做这个check
                checkStateHandler.sendEmptyMessageDelayed(CHECK_STATE, 5);// 5毫秒检查一下
            }
        }
        onScrollListener.onScrollChanged(x, y, oldX, oldY);
    }
}

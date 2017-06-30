package com.dalvu.www.dalvyou.function;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

/**
 * 自定义的LinearLayoutManager
 * Created by user on 2017/6/28.
 */

public class CustomGridLayoutManager extends GridLayoutManager {
    private boolean isScrollEnabled = true;

    public CustomGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }
}

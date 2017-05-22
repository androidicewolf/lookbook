package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.netUtils.StateView;

/**线路详情页中下方展示行程安排等内容的viewPager的适配器
 * Created by user on 2017/5/22.
 */

public class LineContentPagerAdapter extends PagerAdapter {
    private Context context;
    int[] layoutIds;
    public LineContentPagerAdapter(Context context){
        this.context = context;
        layoutIds = new int[]{R.layout.line_plan, R.layout.line_description, R.layout.line_cost, R.layout.line_notice};
    }
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        StateView itemIiew = new StateView(context);
        itemIiew.addNormal(layoutIds[position]);
        container.addView(itemIiew);
        return itemIiew;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}

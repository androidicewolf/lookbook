package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.R;
import com.dalvu.www.dalvyou.netUtils.StateView;

/**线路详情页中下方展示行程安排等内容的viewPager的适配器
 * Created by user on 2017/5/22.
 * http://www.open-open.com/lib/view/open1473233384959.html高度自适应的方法
 */

public class LineContentPagerAdapter extends PagerAdapter {
    private Context context;
    private int[] layoutIds;
    private String[] titles;
    private StateView[] stateViews;
    public LineContentPagerAdapter(Context context){
        this.context = context;
        layoutIds = new int[]{R.layout.line_plan, R.layout.line_description, R.layout.line_cost, R.layout.line_notice};
        titles = new String[]{"行程安排", "产品亮点", "费用说明", "注意事项"};
        stateViews = new StateView[]{new StateView(context), new StateView(context), new StateView(context), new StateView(context)};
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
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
        StateView itemIiew = stateViews[position];
        itemIiew.addNormal(layoutIds[position]);
        itemIiew.showNormal();
        container.addView(itemIiew);
        return itemIiew;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}

package com.dalvu.www.dalvyou.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**线路详情顶部viewpager的适配器
 * Created by user on 2017/5/18.
 */

public class LinePagerAdapter extends PagerAdapter {
    private ArrayList itemsList;
    public LinePagerAdapter(ArrayList itemsList){
        this.itemsList = itemsList;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = (View) itemsList.get(position);
        container.addView(itemView);
        return itemView;
    }
//    private class MyViewPagerItemOnClick implements View.OnClickListener{
//        @Override
//        public void onClick(View v) {
//
//        }
//    }
}

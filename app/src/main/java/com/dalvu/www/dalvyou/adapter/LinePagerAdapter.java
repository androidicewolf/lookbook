package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.dalvu.www.dalvyou.activity.line.LineDetailPictureActivity;

import java.util.ArrayList;

/**线路详情顶部viewpager的适配器
 * Created by user on 2017/5/18.
 */

public class LinePagerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList itemsList;

    public LinePagerAdapter(Context context, ArrayList itemsList) {
        this.context = context;
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
        itemView.setOnClickListener(new MyViewPagerItemOnClick());
        container.addView(itemView);
        return itemView;
    }

    private class MyViewPagerItemOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, LineDetailPictureActivity.class);
            context.startActivity(intent);
        }
    }
}

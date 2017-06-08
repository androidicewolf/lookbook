package com.dalvu.www.dalvyou.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
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
    private Handler handler;

    public LinePagerAdapter(Context context, ArrayList itemsList, Handler handler) {
        this.context = context;
        this.itemsList = itemsList;
        this.handler = handler;
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
        if (itemsList != null && itemsList.size() != 1) {
            itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            //取消handler所有的延时消息
                            handler.removeCallbacksAndMessages(null);
                            break;
                        case MotionEvent.ACTION_UP:
                            handler.sendEmptyMessageDelayed(0, 3000);
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            handler.sendEmptyMessageDelayed(0, 3000);
                            break;
                    }
                    return true;
                }
            });
        }
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

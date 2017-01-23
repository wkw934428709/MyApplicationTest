package com.example.kevin.kk_application.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kevin.kk_application.Activity.R;
import com.gigamole.infinitecycleviewpager.VerticalInfiniteCycleViewPager;

import java.security.AccessControlContext;

/**
 * Created by kevin on 1/6/17.
 */
public class HorizontalPagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    int i = 0;

    public HorizontalPagerAdapter(final Context context, boolean b) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final View view;
        view = mLayoutInflater.inflate(R.layout.item, container, false);
        TextView textView1 = (TextView) view.findViewById(R.id.txt_item);
        textView1.setText("Hello " + i++);
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}

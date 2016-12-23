package com.example.kevin.kk_application;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by kevin on 12/12/16.
 */

class MyViewTouchTestListView extends ListView{
    private static final String TAG = "kk";

    public MyViewTouchTestListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean tt = super.onInterceptTouchEvent(ev);
        Log.e(TAG," ListView    ::  onInterceptTouchEvent " + tt);
        return tt;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean tt = super.onTouchEvent(ev);
        Log.e(TAG,"ListView     ::  onTouchEvent  " + tt);
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        boolean tt = super.dispatchTouchEvent(ev);
        Log.e(TAG,"ListView     ::  dispatchTouchEvent  " + tt);
        return false;
    }
}

package com.example.kevin.kk_application.View;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by kevin on 12/12/16.
 */

class ViewTouchFrame extends LinearLayout {
    private static final String TAG = "kk";

    public ViewTouchFrame(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean tt = super.onInterceptTouchEvent(ev);
        Log.e(TAG,"ViewTouchFrame   ::onInterceptTouchEvent " + tt);
        return tt;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean tt = super.dispatchTouchEvent(ev);
        Log.e(TAG,"ViewTouchFrame   ::dispatchTouchEvent  " + tt);
        return tt;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean tt = super.onTouchEvent(event);
        Log.e(TAG,"ViewTouchFrame   ::onTouchEvent " + tt);
        return tt;
    }
}

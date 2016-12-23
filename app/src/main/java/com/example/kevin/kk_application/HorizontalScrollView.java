package com.example.kevin.kk_application;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by kevin on 12/12/16.
 */

public class HorizontalScrollView extends ViewGroup {

    private static final String TAG = "kk";
    int mChildCount, mChildWith;
    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mLastX;
    private int mLastY;
    private int mChildIndex;

    public HorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG,"new ViewGroup ");
        init();
    }

    private void init() {
        if(mScroller == null){
            mScroller = new Scroller(getContext());
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    @Override
    public void computeScroll() {
        Log.e(TAG,"HHHH::computeScroll ");
//        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        Log.e(TAG,"HHHH::onDetachedFromWindow ");
        mVelocityTracker.recycle();
        super.onDetachedFromWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(TAG,"HHHH::onMeasure ");
/*        int childCount = getChildCount();
        Log.e(TAG," onMeasure :: childCount  : " + childCount);
        int specSize_Width = MeasureSpec.getSize(widthMeasureSpec);
        int specSize_Height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(specSize_Width,specSize_Height);
        Log.e(TAG," onMeasure :: specSize_Width  : " + specSize_Width + " specSize_Height : " + specSize_Height);
        Log.e(TAG," onMeasure :: mode w : " + MeasureSpec.getMode(widthMeasureSpec)+ " mode h : " + MeasureSpec.getMode(heightMeasureSpec));
        Log.e(TAG," onMeasure :: widthMeasureSpec  : " + widthMeasureSpec + " heightMeasureSpec : " + heightMeasureSpec);

        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.measure(100,100);
            measureChildren(widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
*/

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
/*        int childLeft = 0;
        final int childCount = getChildCount();
        mChildCount = childCount;
        for (int j = 0; j < childCount; j++) {
            final View childView = getChildAt(j);
            if(childView.getVisibility() != View.GONE){
                final int childWith = childView.getMeasuredWidth();
                mChildWith = childWith;
                childView.layout(childLeft, 0, childLeft + childWith,
                        + childView.getMeasuredHeight());
                childLeft += childWith;
            }
        }*/
//
//        for (int j = 0; j < getChildCount(); j++) {
//            child = getChildAt(j);
//            child.layout(i, i1, i2, i3);
//        }
        int childLeft = 0;
        final int childCount = getChildCount();
        mChildCount = childCount;

        for (int j = 0; j < childCount; j++) {
            final View childView = getChildAt(j);
            if(childView.getVisibility() != View.GONE){
                final int childWidth = childView.getMeasuredWidth();
                mChildWith = childWidth;
                childView.layout(childLeft, 0, childLeft + childWidth,
                        childView.getMeasuredHeight());
                childLeft += childWidth;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG,"HHHH::onTouchEvent ");
        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"HHHH::ACTION_DOWN ");
                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                break;
            case  MotionEvent.ACTION_MOVE:
                Log.e(TAG,"HHHH::ACTION_MOVE ");
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                scrollTo(-deltaX, 0);
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"HHHH::ACTION_UP ");
                int scrollX = getScrollX();
                int scrollToChildIndex = scrollX / mChildWith;
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                if(Math.abs(xVelocity) >= 50){
                    mChildIndex = xVelocity > 0 ? mChildIndex - 1 : mChildIndex + 1;
                }else {
                    mChildIndex = (scrollX + mChildWith / 2) / mChildWith;
                }
                mChildIndex = Math.max(0, Math.min(mChildIndex, mChildCount - 1));
                int dx = mChildIndex * mChildWith - scrollX;
                smoothScrollBy(dx, 0);
                mVelocityTracker.clear();
                break;
        }

        mLastX = x;
        mLastY = y;
        return true;
    }

    private void smoothScrollBy(int dx, int i) {
        mScroller.startScroll(getScrollX(), 0, dx, 500);
        invalidate();
    }
/*    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean tt = super.onInterceptTouchEvent(ev);
        Log.e(TAG," ViewGroup    ::  onInterceptTouchEvent " + tt);
        return tt;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean tt = super.onTouchEvent(ev);
        Log.e(TAG,"ViewGroup     ::  onTouchEvent  " + tt);
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        boolean tt = super.dispatchTouchEvent(ev);
        Log.e(TAG,"ViewGroup     ::  dispatchTouchEvent  " + tt);
        return false;
    }*/
}

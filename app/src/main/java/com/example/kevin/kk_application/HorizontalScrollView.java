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
    private int mChildCount, mChildWith;
    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mLastX;
    private int mLastY;
    private int mChildIndex;
    private int mCount;

    private static final int TOUCH_STATE_RESET = 0;
    private static final int TOUCH_STATE_SCROLLING = 1;
    private static final int SNAP_VELOCITY = 600;

    private int mTouchState = TOUCH_STATE_RESET;
    private int mCurScreen = 0;

    public HorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG,"new ViewGroup ");
        init();
    }

    private void init() {
        if(mScroller == null){
            mScroller = new Scroller(getContext());
        }
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

//    @Override
//    protected void onDetachedFromWindow() {
//        Log.e(TAG,"HHHH::onDetachedFromWindow ");
//        mVelocityTracker.recycle();
//        super.onDetachedFromWindow();
//    }

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
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                //this startScroll can smooth to the destination
//                mCount++;
//                mScroller.startScroll((mCount - 1) * mChildWith, 0, mChildWith, 0, 3000);
//                invalidate();
                if (mScroller != null) {
                    if(!mScroller.isFinished()){
                        mScroller.abortAnimation();
                    }
                }
                break;
            case  MotionEvent.ACTION_MOVE:
                Log.e(TAG,"HHHH::ACTION_DOWN  x :" + x);
                int scrollX = getScrollX();
                Log.e(TAG,"HHHH::ACTION_DOWN  scrollX : "  + scrollX);
                scrollBy(-(x - mLastX), 0);
                mLastX = x;
                break;
            case MotionEvent.ACTION_UP:
                final VelocityTracker velocityTracker = mVelocityTracker;
                velocityTracker.computeCurrentVelocity(1000);
                int velocityX = (int) velocityTracker.getXVelocity();
                Log.e(TAG, "ACTION_UP velocityX : " + velocityX);
                if(velocityX > SNAP_VELOCITY && mCurScreen > 0){
                    Log.e(TAG," snap to Left");
                    snapToScreen(mCurScreen - 1);
                }else if (velocityX < -SNAP_VELOCITY && mCurScreen < (getChildCount() - 1)){
                    Log.e(TAG," snap to Left");
                    snapToScreen(mCurScreen + 1);
                }else {
                    snapToDestination();
                }
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                mTouchState = TOUCH_STATE_RESET;
                break;
        }

        mLastX = x;
        mLastY = y;
        return true;
    }

    private void snapToDestination() {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        Log.e(TAG, " snapToDestination::scrollX : " + scrollX);
        int destScreen = (scrollX + mChildWith / 2) / mChildWith;
        snapToScreen(destScreen);
        Log.e(TAG, " snapToScreen::destScreen : " + destScreen);
    }

    private void snapToScreen(int position) {
        mCurScreen = position;
        if(mCurScreen > getChildCount() - 1)
            mCurScreen = getChildCount() - 1 ;
        int dx = mCurScreen * mChildWith - getScrollX();
        mScroller.startScroll(getScrollX(), 0, dx, 0, Math.abs(dx) * 2);
        invalidate();
    }

    private void smoothScrollBy(int dx, int i) {
        mScroller.startScroll(getScrollX(), 0, dx, 1000);
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

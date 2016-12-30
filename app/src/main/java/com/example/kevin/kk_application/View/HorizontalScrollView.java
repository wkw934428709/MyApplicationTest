package com.example.kevin.kk_application.View;

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
        Log.e("11"," ViewGroup    ::onTouchEvent " );
//        boolean intercept = false;
//        int x = (int) event.getX();
//        int y = (int) event.getY();
//        if (mVelocityTracker == null) {
//            mVelocityTracker = VelocityTracker.obtain();
//        }
//        mVelocityTracker.addMovement(event);
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                mLastX = x;
//                intercept = false;
//                //this startScroll can smooth to the destination
////                mCount++;
////                mScroller.startScroll((mCount - 1) * mChildWith, 0, mChildWith, 0, 3000);
////                invalidate();
//                if (mScroller != null) {
//                    if(!mScroller.isFinished()){
//                        mScroller.abortAnimation();
//                        intercept = true;
//                    }
//                }
//                Log.e(TAG,"HHHH::ACTION_DOWN  x :" + x);
//                Log.e("11","intercept 1: " + intercept);
//                break;
//            case  MotionEvent.ACTION_MOVE:
//                int deltaX = x - mLastX;
//                int deltaY = y - mLastY;
//                if (Math.abs(deltaX) > Math.abs(deltaY)) {
//                    intercept = true;
//                }else {
//                    intercept = false;
//                }
//                //use By to go to the place compare the last,so it's need this location X subtract last
//                scrollBy(-(x - mLastX), 0);
//                mLastX = x;
//                Log.e("11","intercept 2: " + intercept);
//                break;
//            case MotionEvent.ACTION_UP:
//                intercept = false;
//                //compute the velocity of the smooth to go next page or not
//                final VelocityTracker velocityTracker = mVelocityTracker;
//                velocityTracker.computeCurrentVelocity(1000);
//                int velocityX = (int) velocityTracker.getXVelocity();
//                if(velocityX > SNAP_VELOCITY && mCurScreen > 0){
//                    snapToScreen(mCurScreen - 1);
//                }else if (velocityX < -SNAP_VELOCITY && mCurScreen < (getChildCount() - 1)){
//                    snapToScreen(mCurScreen + 1);
//                }else {
//                    snapToDestination();
//                }
//                if (mVelocityTracker != null) {
//                    mVelocityTracker.recycle();
//                    //it will be FC as IllegalExecption if no following
//                    mVelocityTracker = null;
//                }
//                mTouchState = TOUCH_STATE_RESET;
//                break;
//        }
//        Log.e("11","intercept 3: " + intercept);
//        mLastX = x;
//        mLastY = y;
        return true;
    }

    private void snapToDestination() {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int destScreen = (scrollX + mChildWith / 2) / mChildWith;
        snapToScreen(destScreen);
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
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean tt = super.onInterceptTouchEvent(event);
        Log.e(TAG," ViewGroup    ::  onInterceptTouchEvent " + tt);
        boolean intercept = false;
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                intercept = false;

            return false;
            case  MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    intercept = true;
                }else {
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                mTouchState = TOUCH_STATE_RESET;
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //getParent().requestDisallowInterceptTouchEvent(true);
        boolean tt = super.dispatchTouchEvent(ev);
        Log.e(TAG,"ViewGroup     ::  dispatchTouchEvent  " + tt);
        return tt;
    }
}

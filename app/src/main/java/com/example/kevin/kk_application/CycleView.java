package com.example.kevin.kk_application;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by kevin on 12/15/16.
 */

public class CycleView extends View {

    private int mColor = Color.RED;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CycleView(Context context) {
        super(context);
        Log.e("1", " 1 ");
        init();
    }

    public CycleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        Log.e("1", " 2 ");
    }

    public CycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e("1", " 3");
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CycleView);
        mColor = array.getColor(R.styleable.CycleView_circle_color, Color.YELLOW);
        array.recycle();
        init();
    }

    private void init() {
        mPaint.setColor(mColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("2", " onMeasure");
        // the MeasureSpec is childViewSelf's size and mode
        // its parent MesasureSpec had judge in ViewGroup::getChild
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.e("2", "  widthSpecMode " +widthSpecMode + " heightSpecMode " + heightSpecMode);
        if(widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            // the view's width and height are wrap_content we give a sure size
            Log.e("2", " 1 widthSpecMode " +widthSpecMode);
            setMeasuredDimension(300, 300);
        }else if(widthSpecMode == MeasureSpec.AT_MOST){
            Log.e("2", " 2 heightSpecSize " + heightSpecSize);
//            setMeasuredDimension(200, heightSpecSize);
        }else if(heightSpecMode == MeasureSpec.AT_MOST){
            Log.e("2", " 3");
            setMeasuredDimension(200, 400);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int paddingTop = getPaddingTop();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        int radius = Math.min(width, height) / 2;
        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2, radius, mPaint);
    }

}
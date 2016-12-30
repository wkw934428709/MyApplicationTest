package com.example.kevin.kk_application.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.kevin.kk_application.Activity.R;

public class TouchAndMoveActivity extends AppCompatActivity {

    private ImageView imageView;
    private int mLastX;
    private int mLastY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_and_move);
        init();
    }

    private void init() {
        imageView = (ImageView) findViewById(R.id.imageView2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                Log.d("kk", " move deltaX : " + deltaX + " deltaY : " + deltaY);
                imageView.scrollBy(deltaX,deltaX);
                break;
        }
        return super.onTouchEvent(event);
    }
}

package com.example.kevin.kk_application.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.example.kevin.kk_application.Fragment.MediaPlayerFragment;

/**
 * Created by Kissum on 2017/6/3.
 */
public class MoreInFragments extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    private MediaPlayerFragment mMediaPlayerFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar();
        setContentView(R.layout.draw_view);
        initView();
        initFragment();

    }

    private void initView() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initFragment() {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mMediaPlayerFragment = new MediaPlayerFragment();
//        transaction.add(R.id.moreInFragment, mMediaPlayerFragment);
//        transaction.commitAllowingStateLoss();
    }

    private void setStatusBar() {
        //way2 it's not work we set in xml windowbackground in style
        getWindow().setStatusBarColor(Color.parseColor("#20182023"));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_mediaplayer:
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                mMediaPlayerFragment = new MediaPlayerFragment();
                transaction.add(R.id.moreInFragment, mMediaPlayerFragment);
                transaction.commitAllowingStateLoss();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

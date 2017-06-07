package com.example.kevin.kk_application.Fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kevin.kk_application.Activity.R;

/**
 * Created by Kissum on 2017/6/3.
 */

public class MediaPlayerFragment extends Fragment implements View.OnClickListener{

    private Context mContext;
    private MediaPlayer mMediaPlayer;
    private Button mPlayBt;
    private Button mStopBt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.mediaplayer_fragment, container, false);
        mContext = getContext();
        initView(view);
        return view;
    }

    private void initView(View view) {
        mPlayBt = (Button) view.findViewById(R.id.play);
        mStopBt = (Button) view.findViewById(R.id.play);
        mPlayBt.setOnClickListener(this);
        mStopBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                mMediaPlayer = MediaPlayer.create(mContext, R.raw.romatic);
                mMediaPlayer.start();
                break;

            case R.id.stop:
                mMediaPlayer.stop();
                mMediaPlayer.release();
                mMediaPlayer = null;
                break;

        }
    }
}

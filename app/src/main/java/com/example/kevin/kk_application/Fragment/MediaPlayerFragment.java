package com.example.kevin.kk_application.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kevin.kk_application.Activity.R;

/**
 * Created by Kissum on 2017/6/3.
 */

public class MediaPlayerFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.mediaplayer_fragment, container, false);
        return view;
    }
}

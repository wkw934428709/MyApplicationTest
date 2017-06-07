package com.example.kevin.kk_application.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kevin.kk_application.View.HorizontalScrollView;
import com.example.kevin.kk_application.MyUtils.MyUtils;
import com.example.kevin.kk_application.Activity.R;

import java.util.ArrayList;

public class ViewGroupActivity extends AppCompatActivity {

    private final String TAG = "kk";
    private HorizontalScrollView mListContainer;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);
        Log.e(TAG, " onCreate ");
        mContext = this;
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.start();
//        initView();
        sendNotification();
    }

    private void sendNotification() {
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(mContext);
        builder.setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_menu_camera)
                .setWhen(System.currentTimeMillis())
                .setContentText("Content Text")
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentTitle("Content Tile");
        Notification noti = builder.getNotification();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.content_list_item_for_viewgroup);
        remoteViews.setTextViewText(R.id.name, " ffffff ");
        noti.contentView = remoteViews;
        manager.notify(1,noti);

    }

    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        mListContainer = (HorizontalScrollView) findViewById(R.id.myViewGroup);
        final int screenWidth = MyUtils.getScreenMetrics(this).widthPixels;
        final int screenHeight = MyUtils.getScreenMetrics(this).heightPixels;
        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.content_layout_for_viewgroup,
                    mListContainer, false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = (TextView) layout.findViewById(R.id.textView3);
            textView.setText(i + " Page");
            layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0));
            createList(layout);
            mListContainer.addView(layout);
        }
    }

    private void createList(ViewGroup layout) {
        ListView listView = (ListView) layout.findViewById(R.id.listView);
        ArrayList<String> datas = new ArrayList<String>();
        for (int i = 0; i < 55; i++) {
            datas.add("Name : " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.content_list_item_for_viewgroup, R.id.name, datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ViewGroupActivity.this,
                        "Hello world " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}




package com.anningtex.notificationmanger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

/**
 * @author Administrator
 * desc:Notification
 */
public class OneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        initNotification();
    }

    private void initNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification;
        Intent webViewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.so.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, webViewIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            notification = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.lj)
                    .setTicker("通知栏文字提示")
                    .setContentTitle("title")
                    .setContentText("content")
                    //点击意图
//                    .setContentIntent(pendingIntent)
                    //自动取消
                    .setAutoCancel(false)
                    .build();
        } else {
            notification = new Notification.Builder(this)
                    .setSmallIcon(R.mipmap.lj)
                    .setTicker("通知栏文字提示1")
                    .setContentTitle("title2")
                    .setContentText("content2")
//                    .setContentIntent(pendingIntent)
                    .setAutoCancel(false)
                    .build();
        }

        if (notification != null) {
//            notification.flags = Notification.DEFAULT_LIGHTS & Notification.FLAG_AUTO_CANCEL;
            notification.flags |= Notification.FLAG_ONGOING_EVENT;
            notification.flags |= Notification.FLAG_NO_CLEAR;
            manager.notify(1, notification);
        }
    }
}

package com.anningtex.notificationmanger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

/**
 * @author Administrator
 * desc:Notification通知栏消息
 * 创建一个Notification必不可少的两样东西:
 * 1.NotificationManager  控制Notification的展示和取消
 * 2.NotificationCompat.Builder  用于设置Notification的各种参数
 * Notification点击触发的效果就是通过传入的Intent来控制的
 */
public class TwoActivity extends AppCompatActivity implements View.OnClickListener {
    private Button notification;
    private Button list;
    private Button myView;
    private Button big;
    private Button progress1;
    private Button progress2;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        notification = findViewById(R.id.notification);
        list = findViewById(R.id.list);
        myView = findViewById(R.id.myView);
        big = findViewById(R.id.big);
        progress1 = findViewById(R.id.progress1);
        progress2 = findViewById(R.id.progress2);
        cancel = findViewById(R.id.cancel);

        notification.setOnClickListener(this);
        list.setOnClickListener(this);
        myView.setOnClickListener(this);
        big.setOnClickListener(this);
        progress1.setOnClickListener(this);
        progress2.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notification:
                notificationShow();
                break;
            case R.id.list:
                listNotification();
                break;
            case R.id.myView:
                myviewNotification();
                break;
            case R.id.big:
                bigNotification();
                break;
            case R.id.progress1:
                showprogress1();
                break;
            case R.id.progress2:
                showprogress2();
                break;
            case R.id.cancel:
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.cancelAll();
                notificationManager.cancelAll();
                break;
            default:
                break;
        }
    }

    /**
     * 展示普通的
     */
    private void notificationShow() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, new Intent(), PendingIntent.FLAG_ONE_SHOT);
        builder.setContentTitle("测试")//标题
                .setContentText("测试")//文本
                .setContentIntent(pendingIntent)//启动intent
                .setNumber(10)//这个只是设置通知上的一个值，并不是创建10条notificationmanager   显示数量
                .setTicker("提示文字")//提示文字
                .setWhen(System.currentTimeMillis())//时间
                .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
                .setAutoCancel(false)//是否点击取消
                .setOngoing(false)//于设置是否常驻通知栏,即是否可以侧滑取消/删除
                .setDefaults(Notification.DEFAULT_ALL)//用于设置提示声音闪烁灯以及震动等等,
                .setSmallIcon(R.mipmap.ic_launcher);//图标
        // 1.id,可以通过id取消Notifiation     2.Notification对象
        notificationManager.notify(1, builder.build());
    }

    /**
     * 自定义视图-小
     */
    private void myviewNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, new Intent(), PendingIntent.FLAG_ONE_SHOT);
        builder.setContentTitle("测试")//标题
                .setContentText("测试")//文本
                .setContentIntent(pendingIntent)//启动intent
                .setAutoCancel(false)//是否点击取消
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContent(new RemoteViews(getPackageName(), R.layout.myview));
        notificationManager.notify(2, builder.build());
    }

    /**
     * 大列表视图
     */
    public void listNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setAutoCancel(true);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        builder.setStyle(inboxStyle);
        inboxStyle.setBigContentTitle("测试大标题：");
        for (int i = 0; i < 10; i++) {
            inboxStyle.addLine(String.valueOf(i));
        }
        Intent intent = new Intent(TwoActivity.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pendingIntent);
        notificationManager.notify(3, builder.build());
    }

    /**
     * 自定义大视图
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void bigNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        //无论如何这句代码时不可或缺的
        builder.setSmallIcon(R.mipmap.ic_launcher); // 设置顶部图标
        //创建视图,普通view不行
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout);
        //点击事件
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.button, pendingIntent);
        Notification notify = builder.build();
        notify.contentView = remoteViews; // 视图
        notify.bigContentView = remoteViews; // 大视图
        notify.flags = Notification.FLAG_ONGOING_EVENT;
        notificationManager.notify(4, notify);
    }

    /**
     * 不定长进度条
     */
    private void showprogress1() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, new Intent(), PendingIntent.FLAG_ONE_SHOT);
        builder.setContentTitle("测试")//标题
                .setContentText("测试")//文本
                .setContentIntent(pendingIntent)//启动intent
                .setNumber(10)//这个只是设置通知上的一个值，并不是创建10条notificationmanager   显示数量
                .setTicker("提示文字")//提示文字
                .setWhen(System.currentTimeMillis())//时间
                .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
                .setAutoCancel(false)//是否点击取消
                .setOngoing(false)//于设置是否常驻通知栏,即是否可以侧滑取消/删除
                .setDefaults(Notification.DEFAULT_ALL)//用于设置提示声音闪烁灯以及震动等等,
                .setSmallIcon(R.mipmap.ic_launcher)//图标
                .setProgress(100, 30, true);
        // 1.id,可以通过id取消Notifiation     2.Notification对象
        notificationManager.notify(5, builder.build());
    }

    /**
     * 确定进度的进度条
     */
    private void showprogress2() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, new Intent(), PendingIntent.FLAG_ONE_SHOT);
        builder.setContentTitle("测试")//标题
                .setContentText("测试")//文本
                .setContentIntent(pendingIntent)//启动intent
                .setNumber(10)//这个只是设置通知上的一个值，并不是创建10条notificationmanager   显示数量
                .setTicker("提示文字")//提示文字
                .setWhen(System.currentTimeMillis())//时间
                .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
                .setAutoCancel(false)//是否点击取消
                .setOngoing(false)//于设置是否常驻通知栏,即是否可以侧滑取消/删除
                .setDefaults(Notification.DEFAULT_ALL)//用于设置提示声音闪烁灯以及震动等等,
                .setSmallIcon(R.mipmap.ic_launcher)//图标
                .setProgress(100, 30, false);
        notificationManager.notify(6, builder.build());
        //如果要做下载就将 notificationManager和builder做成全局变量
        //在下载的过程中不断调用builder.setProgress()和notificationManager.notify(1, builder.build());来更新进度
    }
}

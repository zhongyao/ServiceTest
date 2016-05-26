package com.hongri.servicetest;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by zhongyao on 2016/5/26.
 */
public class ForegroundService extends Service {
    public int NOTIFICATION_ID = 1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //创建一个当点击通知栏时将打开MainActivity的Intent
        Intent intent1 = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 1, intent1, 0);
        //设置Notification Builder UI参数
        Notification notification = new Notification.Builder(this).setContentTitle("服务").setContentText("前台运行").setTicker("服务来了").setSmallIcon(R.drawable.icon).addAction(R.drawable.icon, "跳转", pi).build();
        startForeground(NOTIFICATION_ID, notification);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

package com.hongri.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zhongyao on 2016/5/27.
 */
public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            Log.d(MainActivity.TAG, "onStartCommand运行于主线程中");
        } else {
            Log.d(MainActivity.TAG, "onStartCommand运行于子线程中");
        }
        long endTime = System.currentTimeMillis() + 20 * 1000;
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        //此处可知会出现ANR现象
        Log.d(MainActivity.TAG, "MyService中耗时操作执行完毕");
        return super.onStartCommand(intent, flags, startId);
    }
}

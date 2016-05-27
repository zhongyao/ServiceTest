package com.hongri.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;

/**
 * Created by zhongyao on 2016/5/27.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            Log.d(MainActivity.TAG,"onHandleIntent运行于主线程中");
        }else{
            Log.d(MainActivity.TAG,"onHandleIntent运行于子线程中");
        }
        //IntentService会使用单独的线程来执行该方法中的代码
        long endTime = System.currentTimeMillis() + 20 * 1000;
        while (System.currentTimeMillis() < endTime){
            synchronized (this){
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (InterruptedException e) {

                }
            }

        }

        Log.d(MainActivity.TAG,"onHandleIntent方法中耗时任务执行完成");
    }
}

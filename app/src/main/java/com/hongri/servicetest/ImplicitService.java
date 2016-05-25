package com.hongri.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zhongyao on 2016/5/24.
 */
public class ImplicitService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(MainActivity.TAG,"ImplicitService---onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }
}

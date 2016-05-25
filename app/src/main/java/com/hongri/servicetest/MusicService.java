package com.hongri.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Service 可以和Activity绑定，后者会维持一个队前者实例的引用，此引用允许你像对待其他实例化的类一样，
 * 对正在运行的Service进行方法调用。
 */
public class MusicService extends Service {
    private final IBinder binder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(MainActivity.TAG,"MusicService---onBind()");
        return binder;
    }
    public class MyBinder extends Binder {
        MusicService getService() {
            Log.d(MainActivity.TAG,"MusicService---MyBinder---getService()");
            return MusicService.this;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(MainActivity.TAG,"MusicService---onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(MainActivity.TAG,"MusicService---onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(MainActivity.TAG,"MusiceService---onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(MainActivity.TAG,"MusicService---onDestroy()");
    }
}

package com.hongri.servicetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button startService1;
    private Button startService2;
    private Button stopService;
    private Button bindService;
    public static final String TAG = "yao";
    private MusicService musicService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        startService1 = (Button) findViewById(R.id.startService1);
        startService2 = (Button) findViewById(R.id.startService2);
        stopService = (Button) findViewById(R.id.stopService);
        bindService = (Button) findViewById(R.id.bindService);

        startService1.setOnClickListener(this);
        startService2.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         //显式启动Service
         case R.id.startService1:
             Intent intent1 = new Intent(this,ExplicitService.class);
             startService(intent1);
             break;
         //隐式启动Service(貌似Android5.0之后隐式启动Service会报错)
         case R.id.startService2:
//             Intent intent2 = new Intent("com.android.ImplicitService");
//             startService(intent2);
             break;
         case R.id.stopService:
             stopService(new Intent(this,ExplicitService.class));

             break;
         //MainActivity绑定Service
         case R.id.bindService:
             Intent bindIntent = new Intent(this,MusicService.class);
             bindService(bindIntent,connection, Context.BIND_AUTO_CREATE);
             break;
     }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG,"onServiceConnected");
            musicService = ((MusicService.MyBinder)service).getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG,"onServiceDisconnected");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}

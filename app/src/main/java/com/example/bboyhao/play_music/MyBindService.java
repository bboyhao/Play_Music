package com.example.bboyhao.play_music;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class MyBindService extends Service {
    private static final String TAG = "MyBindService";
    private IBinder myBinder;
    private Random generator;

    @Override
    public void onCreate(){
        myBinder = new MyBinder();
        generator = new Random();
        Log.e(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e(TAG, "onRebind");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestory");
        super.onDestroy();
    }

    public int getRandomInt(){
        return generator.nextInt(100);
    }

    public class MyBinder extends Binder {
        MyBindService getService(){
            return MyBindService.this;
        }
    }
}

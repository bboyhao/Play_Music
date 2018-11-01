package com.example.bboyhao.play_music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public static final String TAG = "MyService";
    private int startId;

    private MediaPlayer mediaPlayer;

    public enum Control{
        PLAY, PAUSE, STOP
    }

    public MyService() {
    }

    @Override
    public void onCreate() {
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this, R.raw.rap);
            mediaPlayer.setLooping(false);
        }
        Log.e(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.startId = startId;
        Bundle bundle = intent.getExtras();
        Log.e(TAG, "onStartCommand---startId " + startId);
        if(bundle != null){
            Control control = (Control) bundle.getSerializable("KEY");
            if(control != null){
                switch(control){
                    case PAUSE:
                        pause();
                        break;
                    case PLAY:
                        play();
                        break;
                    case STOP:
                        stop();
                        break;
                }
            }

        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onDestroy();
    }

    private void pause(){
        if(mediaPlayer != null&&mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    private void play(){
        if(mediaPlayer != null && !mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    private void stop(){
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
        stopSelf(startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

package com.example.bboyhao.play_music;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private int i = 1;
    private Button play;
    private Button pause;
    private Button stop;
    private Button intent_service;
    private MyBindService mService;
    private boolean isBinded = false;
    private MyServiceConnection mConnection = new MyServiceConnection();
    private Button bindService;
    private Button getRandomInt;
    private Button unbindService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.play_btn);
        pause = findViewById(R.id.pause_btn);
        stop = findViewById(R.id.stop_btn);
        intent_service = findViewById(R.id.intent_service);
        bindService = findViewById(R.id.bind_service);
        getRandomInt = findViewById(R.id.get_random_int);
        unbindService = findViewById(R.id.unbind_service);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("KEY", MyService.Control.PLAY);
                Intent intent = new Intent(MainActivity.this, MyService.class);
                intent.putExtras(bundle);
                startService(intent);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("KEY", MyService.Control.PAUSE);
                Intent intent = new Intent(MainActivity.this, MyService.class);
                intent.putExtras(bundle);
                startService(intent);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("KEY", MyService.Control.STOP);
                Intent intent = new Intent(MainActivity.this, MyService.class);
                intent.putExtras(bundle);
                startService(intent);
            }
        });

        intent_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyIntentService.class);
                Bundle bundle = new Bundle();
                bundle.putString("KEY", "value of current of i is "+i);
                ++i;
                intent.putExtras(bundle);
                startService(intent);
            }
        });

        bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService();
            }
        });

        getRandomInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRandomNumber();
            }
        });

        unbindService.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                unbindService();
            }
        });
    }

    public void bindService(){
        Intent intent = new Intent(MainActivity.this, MyBindService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public void unbindService(){
        if(isBinded){
            unbindService(mConnection);
            isBinded = false;
        }
    }

    public void showRandomNumber(){
        if(isBinded)
            Toast.makeText(this, "The ramdom integer is " + mService.getRandomInt(),
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Not bind to MyService", Toast.LENGTH_SHORT).show();
    }

    private class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBindService.MyBinder mBinder = (MyBindService.MyBinder) service;
            mService = mBinder.getService();
            isBinded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBinded = false;
        }
    }
}

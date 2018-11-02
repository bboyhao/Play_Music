package com.example.bboyhao.play_music;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private int i = 1;
    private Button play;
    private Button pause;
    private Button stop;
    private Button intent_service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.play_btn);
        pause = findViewById(R.id.pause_btn);
        stop = findViewById(R.id.stop_btn);
        intent_service = findViewById(R.id.intent_service);

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
    }
}

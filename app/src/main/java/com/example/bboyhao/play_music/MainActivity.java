package com.example.bboyhao.play_music;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button play;
    private Button pause;
    private Button stop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.play_btn);
        pause = findViewById(R.id.pause_btn);
        stop = findViewById(R.id.stop_btn);

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
    }
}

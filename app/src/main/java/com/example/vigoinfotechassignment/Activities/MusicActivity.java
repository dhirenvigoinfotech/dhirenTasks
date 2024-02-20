package com.example.vigoinfotechassignment.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vigoinfotechassignment.R;
import com.example.vigoinfotechassignment.Services.MusicService;

public class MusicActivity extends AppCompatActivity {

    Button btnstart, btnstop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        btnstart = findViewById(R.id.btnstart);
        btnstop = findViewById(R.id.btnstop);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(new Intent(MusicActivity.this, MusicService.class));
                }else {
                    startService(new Intent(MusicActivity.this, MusicService.class));
                }
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MusicActivity.this, MusicService.class));
            }
        });
    }
}
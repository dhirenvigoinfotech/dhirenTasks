package com.example.vigoinfotechassignment.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vigoinfotechassignment.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLaunchProject1 = findViewById(R.id.btnLaunchProject1);
        Button btnLaunchProject2 = findViewById(R.id.btnLaunchProject2);
        Button btnLaunchProject3 = findViewById(R.id.btnLaunchProject3);
        Button btnLaunchProject4 = findViewById(R.id.btnLaunchProject4);

        btnLaunchProject1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchProject1();
            }
        });

        btnLaunchProject2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnectedToInternet()){
                    launchProject2();
                }else {
                    Toast.makeText(MainActivity.this, "Please turn on Internet", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnLaunchProject3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchProject3();
            }
        });

        btnLaunchProject4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchProject4();
            }
        });
    }

    private void launchProject1(){
        Toast.makeText(MainActivity.this, "SQLite task is Opened", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.vigoinfotechassignment","com.example.vigoinfotechassignment.Activities.NoteListActivity"));
        startActivity(intent);
    }

    private void launchProject2(){
        Toast.makeText(MainActivity.this, "API task is Opened", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.vigoinfotechassignment","com.example.vigoinfotechassignment.Activities.ApiOutputActivity"));
        startActivity(intent);
    }

    private void launchProject3(){
        Toast.makeText(MainActivity.this, "Data-Passing task is Opened", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.vigoinfotechassignment","com.example.vigoinfotechassignment.Activities.InputActivity"));
        startActivity(intent);
    }

    private void launchProject4(){
        Toast.makeText(MainActivity.this, "Service task is Opened", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.vigoinfotechassignment","com.example.vigoinfotechassignment.Activities.MusicActivity"));
        startActivity(intent);
    }

    private boolean isConnectedToInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null){
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }
}

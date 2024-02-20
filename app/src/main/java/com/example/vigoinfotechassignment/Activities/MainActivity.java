package com.example.vigoinfotechassignment.Activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vigoinfotechassignment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    // TODO: Inform user that that your app will not show notifications.
                }
            });

    private void askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLaunchProject1 = findViewById(R.id.btnLaunchProject1);
        Button btnLaunchProject2 = findViewById(R.id.btnLaunchProject2);
        Button btnLaunchProject3 = findViewById(R.id.btnLaunchProject3);
        Button btnLaunchProject4 = findViewById(R.id.btnLaunchProject4);
        Button btnLaunchProject5 = findViewById(R.id.btnLaunchProject5);

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

        btnLaunchProject5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throw new RuntimeException("Test Crash");
            }
        });

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            System.out.println("Fetching FCM registration token failed");
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        Toast.makeText(MainActivity.this, "Notification will be shared", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(MainActivity.this, "Services task is Opened", Toast.LENGTH_SHORT).show();
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

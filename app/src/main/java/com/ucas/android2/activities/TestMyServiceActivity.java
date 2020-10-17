package com.ucas.android2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.ucas.android2.R;
import com.ucas.android2.services.MyService;

public class TestMyServiceActivity extends AppCompatActivity {

    Context context = this;
    ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_my_service);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.e("onServiceConnected", "Done");
                MyService.LocalBinder localBinder = (MyService.LocalBinder) iBinder;
                MyService myService = localBinder.getService();
                Log.e("testConnection", myService.testConnection() + "");

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(context, MyService.class));
            }
        });

        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(context, MyService.class));
            }
        });

        findViewById(R.id.bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindService(new Intent(context, MyService.class)
                        , serviceConnection
                        , BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.unbind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(serviceConnection);
            }
        });

    }
}
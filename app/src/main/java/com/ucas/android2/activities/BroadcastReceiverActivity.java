package com.ucas.android2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ucas.android2.R;
import com.ucas.android2.receivers.SmsReceiver;

public class BroadcastReceiverActivity extends AppCompatActivity {
    SmsReceiver smsReceiver = new SmsReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver3);

//        Intent intent = new Intent(getString(R.string.CUSTEM_ACTION));
//        sendBroadcast(intent);


        //Dynamic Register
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
//        registerReceiver(smsReceiver, filter);

        requestSmsPermission();
    }

    private void requestSmsPermission() {
        String readSms = Manifest.permission.READ_SMS;
        String receiveSms = Manifest.permission.RECEIVE_SMS;
        int grant = ContextCompat.checkSelfPermission(this, readSms) + ContextCompat.checkSelfPermission(this, receiveSms);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{readSms, receiveSms}, 1);
        } else {
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            Log.d("", "Permission granted");
        }
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        unregisterReceiver(smsReceiver);
//    }
}
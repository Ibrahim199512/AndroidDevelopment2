package com.ucas.android2.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    IBinder iBinder = new LocalBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("MyService", "onBind");
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("MyService", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyService", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("MyService", "onStartCommand");
        Log.e("startId", "" + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    public class LocalBinder extends Binder {
        public MyService getService() {
            // Return this instance of LocalService so clients can call public methods
            return MyService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MyService", "onDestroy");
    }

    public String testConnection() {
        return "Done testConnection";
    }


}

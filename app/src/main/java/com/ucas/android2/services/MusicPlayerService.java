package com.ucas.android2.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.ucas.android2.R;

public class MusicPlayerService extends Service {
    IBinder iBinder = new MusicPlayerService.LocalBinder();
    MediaPlayer mediaPlayer;

    int[] songs = {R.raw.inception_time
            , R.raw.pirates_of_the_caribbean
            , R.raw.requiem_for_a_dream};

    int postion = 0;

    public MusicPlayerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("MusicPlayerService", "onBind");
        mediaPlayer = MediaPlayer.create(this, songs[postion]);
        mediaPlayer.start();
        return iBinder;
    }

    public class LocalBinder extends Binder {
        public MusicPlayerService getService() {
            // Return this instance of LocalService so clients can call public methods
            return MusicPlayerService.this;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("MusicPlayerService", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MusicPlayerService", "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MusicPlayerService", "onDestroy");
    }

    public void pauseSong() {
        mediaPlayer.pause();
    }

    public void startSong() {
        mediaPlayer.start();
    }

    public void nextSong() {
        if (postion < (songs.length - 1)) {
            postion++;
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(this, songs[postion]);
            mediaPlayer.start();
        } else {
            Toast.makeText(this, "This is the last songe", Toast.LENGTH_SHORT).show();
        }
    }

    public void perSong() {
        if (postion != 0) {
            postion--;
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(this, songs[postion]);
            mediaPlayer.start();
        } else {
            Toast.makeText(this, "This is the first songe", Toast.LENGTH_SHORT).show();
        }
    }


    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }


    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    public void seekTo(int msec) {
        mediaPlayer.seekTo(msec);
    }
}

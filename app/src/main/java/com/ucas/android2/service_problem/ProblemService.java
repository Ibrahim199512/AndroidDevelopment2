package com.ucas.android2.service_problem;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.ucas.android2.R;
import com.ucas.android2.services.MusicPlayerService;
import com.ucas.android2.services.ProblemInterface;

public class ProblemService extends Service {
    IBinder iBinder = new ProblemService.LocalBinder();
    MediaPlayer myMediaPlayer;
    ProblemInterface problemInterface;

    int[] songs = {R.raw.inception_time
            , R.raw.pirates_of_the_caribbean
            , R.raw.requiem_for_a_dream};
    int postion = 0;

    public ProblemService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("MusicPlayerService", "onBind");
        myMediaPlayer = MediaPlayer.create(this, songs[postion]);
        setOnCompletionListener();
        myMediaPlayer.start();

        return iBinder;
    }

    public class LocalBinder extends Binder {
        public ProblemService getService() {
            // Return this instance of LocalService so clients can call public methods
            return ProblemService.this;
        }
    }

    public void pauseSong() {
        myMediaPlayer.pause();
    }

    public void startSong() {
        myMediaPlayer.start();
    }

    public int getCurrentPosition() {
        return myMediaPlayer.getCurrentPosition();
    }


    public int getDuration() {
        return myMediaPlayer.getDuration();
    }

    public boolean isPlaying() {
        return myMediaPlayer.isPlaying();
    }

    public void seekTo(int msec) {
        myMediaPlayer.seekTo(msec);
    }

    public void nextSong() {
        if (postion < (songs.length - 1)) {
            postion++;
            myMediaPlayer.stop();
            myMediaPlayer = MediaPlayer.create(this, songs[postion]);
            setOnCompletionListener();
            myMediaPlayer.start();
        } else {
            Toast.makeText(this, "This is the last songe", Toast.LENGTH_SHORT).show();
        }
    }

    public void perSong() {
        if (postion != 0) {
            postion--;
            myMediaPlayer.stop();
            myMediaPlayer = MediaPlayer.create(this, songs[postion]);
            setOnCompletionListener();
            myMediaPlayer.start();
        } else {
            Toast.makeText(this, "This is the first songe", Toast.LENGTH_SHORT).show();
        }


    }

    public int getPostion() {
        return postion;
    }

    public void setLooping(boolean b) {
        myMediaPlayer.setLooping(b);
    }

    public void setOnCompletionListener() {
        myMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (!myMediaPlayer.isLooping()) {
                    if (postion < (songs.length - 1)) {
                        postion++;
                        myMediaPlayer.stop();
                        myMediaPlayer = MediaPlayer.create(ProblemService.this, songs[postion]);
                        myMediaPlayer.start();
                    } else {
                        postion = 0;
                        myMediaPlayer.stop();
                        myMediaPlayer = MediaPlayer.create(ProblemService.this, songs[postion]);
                        myMediaPlayer.start();
                    }
                    setOnCompletionListener();
                    problemInterface.sendSomeText();
                }
            }
        });
    }

    public void setProblemInterface(ProblemInterface problemInterface) {
        this.problemInterface = problemInterface;
    }
}
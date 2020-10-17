package com.ucas.android2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ucas.android2.R;
import com.ucas.android2.services.MusicPlayerService;
import com.ucas.android2.services.MyService;

import java.util.concurrent.TimeUnit;

public class MusicPlayerActivity extends AppCompatActivity {
    Context context = this;
    ServiceConnection serviceConnection;
    MusicPlayerService musicPlayerService;
    SeekBar seekbar;
    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        seekbar = findViewById(R.id.seekbar);
        time = findViewById(R.id.time);


        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.e("onServiceConnected", "Done");
                MusicPlayerService.LocalBinder localBinder = (MusicPlayerService.LocalBinder) iBinder;
                musicPlayerService = localBinder.getService();
                seekbar.setMax(musicPlayerService.getDuration());
                final Handler mHandler = new Handler();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        seekbar.setProgress(musicPlayerService.getCurrentPosition());
                        time.setText(TimeUnit.MILLISECONDS.toSeconds(musicPlayerService.getCurrentPosition()) + " s");
                        mHandler.postDelayed(this, 1000);
                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };

        bindService(new Intent(context, MusicPlayerService.class)
                , serviceConnection
                , BIND_AUTO_CREATE);


        findViewById(R.id.play_song).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlayerService.startSong();
            }
        });


        findViewById(R.id.next_song).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlayerService.nextSong();
                seekbar.setMax(musicPlayerService.getDuration());
            }
        });

        findViewById(R.id.per_song).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlayerService.perSong();
                seekbar.setMax(musicPlayerService.getDuration());
            }
        });

        findViewById(R.id.pause_song).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlayerService.pauseSong();
            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                musicPlayerService.seekTo(seekBar.getProgress());
            }
        });

    }
}
package com.ucas.android2.service_problem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ucas.android2.R;
import com.ucas.android2.services.MusicPlayerService;
import com.ucas.android2.services.ProblemInterface;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ServiceActivity extends AppCompatActivity {
    ServiceProblemAdapter testAdapter;

    ServiceConnection serviceConnection;
    ProblemService musicPlayerService;
    SeekBar seekbar;
    TextView time;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        RecyclerView recyclerview = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL
                , false);
        testAdapter = new ServiceProblemAdapter(this, strings);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(testAdapter);

        seekbar = findViewById(R.id.seekbar);
        time = findViewById(R.id.time);


        final ProblemInterface problemInterface = new ProblemInterface() {
            @Override
            public void sendSomeText() {
                testAdapter.setSelectedPosition(musicPlayerService.getPostion());
                seekbar.setMax(musicPlayerService.getDuration());

            }
        };
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.e("onServiceConnected", "Done");
                ProblemService.LocalBinder localBinder = (ProblemService.LocalBinder) iBinder;
                musicPlayerService = localBinder.getService();
                musicPlayerService.setProblemInterface(problemInterface);
                seekbar.setMax(musicPlayerService.getDuration());
                final Handler mHandler = new Handler();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("Test", musicPlayerService.getCurrentPosition() + "");
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


        findViewById(R.id.play_pause_song).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (musicPlayerService.isPlaying()) {
                    musicPlayerService.pauseSong();
                } else {
                    musicPlayerService.startSong();
                }
            }
        });

        findViewById(R.id.next_song).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlayerService.nextSong();
                int postion = musicPlayerService.getPostion();
                testAdapter.setSelectedPosition(postion);
                seekbar.setMax(musicPlayerService.getDuration());
            }
        });

        findViewById(R.id.per_song).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicPlayerService.perSong();
                int postion = musicPlayerService.getPostion();
                testAdapter.setSelectedPosition(postion);
                seekbar.setMax(musicPlayerService.getDuration());
            }
        });


        bindService(new Intent(context, ProblemService.class)
                , serviceConnection
                , BIND_AUTO_CREATE);


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


        CheckBox checkBox = findViewById(R.id.repate);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d("onCheckedChanged", b + "");
                musicPlayerService.setLooping(b);
            }
        });
    }
}
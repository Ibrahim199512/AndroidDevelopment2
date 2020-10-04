package com.ucas.android2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImageGridLayoutManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_grid_layout_manager);


        final ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        strings.add("6");
        strings.add("7");
        strings.add("8");
        strings.add("9");
        strings.add("10");
        strings.add("11");
        strings.add("12");
        strings.add("13");
        strings.add("13");
        strings.add("13");
        strings.add("13");
        strings.add("13");
        strings.add("13");
        strings.add("13");
        strings.add("13");
        strings.add("13");
        strings.add("13");
        RecyclerView recyclerview = findViewById(R.id.recyclerview);
        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL
//                , false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        final ImagesAdapter testAdapter = new ImagesAdapter(this,strings);
        recyclerview.setLayoutManager(gridLayoutManager);
        recyclerview.setAdapter(testAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(ImageGridLayoutManagerActivity.this, "Done", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
                strings.add("asdas");
                testAdapter.notifyDataSetChanged();
            }
        });

    }
}
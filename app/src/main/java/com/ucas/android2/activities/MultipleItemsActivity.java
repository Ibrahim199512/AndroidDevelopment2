package com.ucas.android2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ucas.android2.R;
import com.ucas.android2.adapters.MultipleItemsAdapter;

import java.util.ArrayList;

public class MultipleItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_items);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("");
        strings.add("6");
        strings.add("7");
        strings.add("");
        strings.add("9");
        strings.add("10");
        strings.add("");
        strings.add("12");
        strings.add("13");
        strings.add("");
        strings.add("13");
        strings.add("13");
        strings.add("13");
        strings.add("13");
        strings.add("13");
        strings.add("13");
        strings.add("13");
        strings.add("13");
        RecyclerView recyclerview = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL
                , false);
        MultipleItemsAdapter multipleItemsAdapter = new MultipleItemsAdapter(strings);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(multipleItemsAdapter);


    }
}
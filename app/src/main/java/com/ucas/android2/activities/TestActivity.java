package com.ucas.android2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ucas.android2.R;
import com.ucas.android2.databinding.ActivityTestBinding;

public class TestActivity extends AppCompatActivity {

    private ActivityTestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TestActivity.this, "Test", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
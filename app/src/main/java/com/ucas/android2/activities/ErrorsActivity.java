package com.ucas.android2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;

import com.ucas.android2.R;

public class ErrorsActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_errors);
//        divByZero();
//        indexOutOfBounds();
//        nullPointer1();
//        nullPointer2();
//        nullPointer3();
//        nullPointer4();
//        nullPointer5();
        intDiv();
    }

    public void divByZero() {
        int number1 = 15;
        int number2 = 0;
        int div = number1 / number2;
        Log.e("divByZero", "the calue of " + number1 + "/" + number2 + " = " + div);
    }

    public void indexOutOfBounds() {
        int[] tempArray = new int[10];
        tempArray[10] = 50;
    }

    public void nullPointer1() {
        int[] tempArray = null;
        tempArray[10] = 50;
    }

    public void nullPointer2() {
        String s = null;
        Log.d("nullPointer2", s);
    }

    public void nullPointer3() {
        Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show();
    }

    public void nullPointer4() {
        SeekBar seekBar = findViewById(R.id.seekbar);
        seekBar.setMax(100);
    }

    private void nullPointer5() {
        Intent intent = new Intent(this, MainActivity3.class);
        intent.putExtra("name", "Ibrahim");
        startActivity(intent);
    }

    public void intDiv() {
        int number1 = 15;
        int number2 = 16;
        int div = number1 / number2;
        Log.e("intDiv", "the value of " + number1 + "/" + number2 + " = " + div);
    }
}
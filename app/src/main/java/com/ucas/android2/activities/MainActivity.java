package com.ucas.android2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ucas.android2.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        whileExample();
//        forExample();
//        try {
//            tryCatchExample();
//        } catch (Exception e) {
//
//        }
//        intentExample();
        Log.d("isPrimeNumbers", isPrimeNumbers(0) + "");

    }

    private boolean isPrimeNumbers(int number) {
        if (number <= 1)
            return false;
        for (int i = 2; i <= number/2 ; i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }

    private void intentExample() {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("name", "Ibrahim");
        startActivity(intent);
    }

    private void whileExample() {
        int count = 0;
        while (count <= 10) {
            if (count == 5) {
                count++;
                continue;
            }
            Log.d("Counter", count + "");
            count++;
        }
    }

    private void forExample() {
        for (int i = 0; i < 10; i++) {
            Log.d("i", i + "");
            for (int j = 0; j < 10; j++) {
                Log.d("j", " " + j + "");
            }
        }
    }


    private void tryCatchExample() throws Exception {
        try {
//            throw new RuntimeException();
        } catch (Exception e) {
            Log.e("catch", e.toString());
        } finally {
            Log.d("finally", "Done");
        }
    }

}
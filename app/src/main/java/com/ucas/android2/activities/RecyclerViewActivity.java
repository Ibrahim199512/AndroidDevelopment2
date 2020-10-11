package com.ucas.android2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.ucas.android2.R;
import com.ucas.android2.adapters.StudentsAdapter;
import com.ucas.android2.interfaces.StudentInterface;
import com.ucas.android2.modules.Student;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("1");
//        strings.add("2");
//        strings.add("3");
//        strings.add("4");
//        strings.add("5");
//        strings.add("6");
//        strings.add("7");
//        strings.add("8");
//        strings.add("9");
//        strings.add("10");
//        strings.add("11");
//        strings.add("12");
//        strings.add("13");
//        strings.add("13");
//        strings.add("13");
//        strings.add("13");
//        strings.add("13");
//        strings.add("13");
//        strings.add("13");
//        strings.add("13");
//        strings.add("13");
//        strings.add("13");
//        RecyclerView recyclerview = findViewById(R.id.recyclerview);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL
//                , false);
//        TestAdapter testAdapter = new TestAdapter(strings);
//        recyclerview.setLayoutManager(layoutManager);
//        recyclerview.setAdapter(testAdapter);

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(new Student("A", "20"));
        studentList.add(new Student("B", "20"));
        studentList.add(new Student("C", "20"));
        studentList.add(new Student("D", "25"));
        studentList.add(new Student("H", "26"));
        studentList.add(new Student("A", "20"));
        studentList.add(new Student("A", "30"));
        RecyclerView recyclerview = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL
                , false);
        StudentsAdapter studentsAdapter = new StudentsAdapter(studentList, new StudentInterface() {
            @Override
            public void sendSomeText(String text) {
                Toast.makeText(RecyclerViewActivity.this, "" + text, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(studentsAdapter);


    }
}
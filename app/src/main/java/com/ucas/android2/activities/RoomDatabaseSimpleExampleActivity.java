package com.ucas.android2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ucas.android2.R;
import com.ucas.android2.database.DatabaseClient;
import com.ucas.android2.modules.TestItem;
import com.ucas.android2.modules.User;

import java.util.List;

public class RoomDatabaseSimpleExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_database_simple_example);

        findViewById(R.id.insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });

        findViewById(R.id.read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                readAll();
                ReadUsingAsyncTask readUsingAsyncTask = new ReadUsingAsyncTask();
                readUsingAsyncTask.execute();
            }
        });

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readAndUpdate();
            }
        });

        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readAndDelete();
            }
        });
    }

    private void insert() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .testItemDao()
                        .insert(new TestItem("title", "", true));

                DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .testItemDao()
                        .insert(new TestItem("title", "", false));

                Log.e("insert", "Done");
            }
        });
        thread.start();
    }


    private void readAll() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<TestItem> testItems = DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .testItemDao()
                        .getAll();
                for (int i = 0; i < testItems.size(); i++) {
                    Log.e("id", testItems.get(i).getId() + "");
                    Log.e("Title", testItems.get(i).getTitle() + "");
                    Log.e("Description", testItems.get(i).getDescription() + "");
                }
            }
        });
        thread.start();
    }

    private void readAndUpdate() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<TestItem> testItems = DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .testItemDao()
                        .getAll();

                TestItem temp = null;
                if (!testItems.isEmpty()) {
                    temp = testItems.get(testItems.size() - 1);
                    temp.setTitle("Updated");
                }

                DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .testItemDao()
                        .update(temp);

                Log.e("update", "Done");
            }
        });
        thread.start();
    }

    private void readAndDelete() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<TestItem> testItems = DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .testItemDao()
                        .getAll();

                TestItem temp = null;
                if (!testItems.isEmpty()) {
                    temp = testItems.get(testItems.size() - 1);
//                    temp.setTitle("Updated");
                }

                DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .testItemDao()
                        .delete(temp);

                Log.e("delete", "Done");
            }
        });
        thread.start();
    }


    class ReadUsingAsyncTask extends AsyncTask<Void, Void, List<TestItem>> {

        @Override
        protected List<TestItem> doInBackground(Void... voids) {
            List<TestItem> testItems = DatabaseClient.getInstance(getApplicationContext())
                    .getAppDatabase()
                    .testItemDao()
                    .getAll();
            return testItems;
        }

        @Override
        protected void onPostExecute(List<TestItem> testItems) {
            super.onPostExecute(testItems);
            for (int i = 0; i < testItems.size(); i++) {
                Log.e("id", testItems.get(i).getId() + "");
                Log.e("Title", testItems.get(i).getTitle() + "");
                Log.e("Description", testItems.get(i).getDescription() + "");
            }
        }
    }

}
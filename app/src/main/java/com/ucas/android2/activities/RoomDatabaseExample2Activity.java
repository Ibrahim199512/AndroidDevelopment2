package com.ucas.android2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ucas.android2.R;
import com.ucas.android2.database.DatabaseClient;
import com.ucas.android2.modules.Account;
import com.ucas.android2.modules.TestItem;
import com.ucas.android2.modules.User;
import com.ucas.android2.modules.UserWithAccounts;

import java.util.List;

public class RoomDatabaseExample2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_database_example2);

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });
        findViewById(R.id.read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readAll();
            }
        });

    }

    private void insert() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .userDao()
                        .insert(new User(2));

                DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .accountDao()
                        .insert(new Account("Account5", 2));

                DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .accountDao()
                        .insert(new Account("Account6", 2));

                DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .accountDao()
                        .insert(new Account("Account7", 2));

                Log.e("insert", "Done");
            }
        });
        thread.start();
    }

    private void readAll() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<UserWithAccounts> userWithAccountsList = DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .userDao()
                        .loadUsersWithAccounts();
                for (int i = 0; i < userWithAccountsList.size(); i++) {
                    Log.e("getUserId", userWithAccountsList.get(i).getUser().getUserId() + "");

                    for (int j = 0; j < userWithAccountsList.get(i).getAccountsList().size(); j++) {
                        Log.e("Title", userWithAccountsList.get(i).getAccountsList().get(j).getDescription() + "");
                    }
                }
            }
        });
        thread.start();
    }


}
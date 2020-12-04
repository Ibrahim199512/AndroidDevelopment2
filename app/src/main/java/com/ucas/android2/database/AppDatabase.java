package com.ucas.android2.database;

import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.RoomDatabase;

import com.ucas.android2.interfaces.AccountDao;
import com.ucas.android2.interfaces.TestItemDao;
import com.ucas.android2.interfaces.UserDao;
import com.ucas.android2.modules.Account;
import com.ucas.android2.modules.TestItem;
import com.ucas.android2.modules.User;

@Database(entities = {TestItem.class, User.class, Account.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TestItemDao testItemDao();

    public abstract UserDao userDao();

    public abstract AccountDao accountDao();

}
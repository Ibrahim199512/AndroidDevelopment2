package com.ucas.android2.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.ucas.android2.modules.Account;

@Dao
public interface AccountDao {
    @Insert
    void insert(Account account);

    @Delete
    void delete(Account account);

    @Update
    void update(Account account);
}
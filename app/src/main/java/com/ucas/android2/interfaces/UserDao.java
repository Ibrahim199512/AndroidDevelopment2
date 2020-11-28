package com.ucas.android2.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ucas.android2.modules.User;
import com.ucas.android2.modules.UserWithAccounts;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    public List<UserWithAccounts> loadUsersWithAccounts();

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);
}
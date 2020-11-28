package com.ucas.android2.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ucas.android2.modules.TestItem;

import java.util.List;

@Dao
public interface TestItemDao {
    @Query("SELECT * FROM TestItem")
    List<TestItem> getAll();

    @Insert
    void insert(TestItem testItem);

    @Delete
    void delete(TestItem testItem);

    @Update
    void update(TestItem testItem);
}
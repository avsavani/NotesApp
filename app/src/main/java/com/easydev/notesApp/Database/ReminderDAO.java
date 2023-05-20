package com.easydev.notesApp.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import com.easydev.notesApp.Models.Reminder;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ReminderDAO {
    @Insert(onConflict = REPLACE)
    void insert(Reminder reminder);

    @Delete
    void delete(Reminder reminder);
}

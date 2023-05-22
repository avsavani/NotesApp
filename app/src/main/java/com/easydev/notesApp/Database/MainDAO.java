package com.easydev.notesApp.Database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.easydev.notesApp.Models.Notes;

import java.util.List;
@Dao
public interface MainDAO {
    @Insert(onConflict = REPLACE)
    void insert(Notes notes);

    @Delete
    void delete(Notes notes);

    @Delete
    void reset(List<Notes> notesList);

    @Query("UPDATE notes SET title = :title, notes = :note WHERE notesID = :id")
    void update(int id, String title, String note);

    @Query("UPDATE notes SET pin = :pin WHERE notesID = :id")
    void pin(int id, boolean pin);

    @Query("SELECT * FROM notes ORDER BY notesID DESC")
    List<Notes> getAll();

}

package com.easydev.notesApp.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.easydev.notesApp.Models.Users;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDAO {
    @Insert(onConflict = REPLACE)
    void insert(Users users);

    @Delete
    void delete(Users users);

    @Delete
    void reset(List<Users> usersList);

    @Query("SELECT EXISTS(SELECT * from Users where name=:name)")
    boolean is_taken_name(String name);

    @Query("SELECT EXISTS(SELECT * from Users where email=:email)")
    boolean is_taken_email(String email);

    @Query("SELECT EXISTS(SELECT * from Users where name=:name AND password=:password)")
    boolean login(String name,String password);

    @Query("SELECT * FROM notes ORDER BY id DESC")
    List<Users> getAll();

}

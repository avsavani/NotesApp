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

    @Query("UPDATE users SET name = :name, email = :email, password= :password WHERE ID = :id")
    void update(int id, String name, String email, String password);

    @Query("SELECT * FROM notes ORDER BY id DESC")
    List<Users> getAll();

}

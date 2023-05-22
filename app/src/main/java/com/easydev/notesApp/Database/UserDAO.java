package com.easydev.notesApp.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.easydev.notesApp.Models.Relations.UsersNotesCrossref;
import com.easydev.notesApp.Models.Relations.UsersWithNotes;
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

    @Query("SELECT * FROM Users WHERE name = :name")
    public Users getEntry(String name);

    @Query("SELECT EXISTS(SELECT * from Users where name=:name AND password=:password)")
    boolean login(String name,String password);

    @Query("SELECT * FROM Users ORDER BY usersID DESC")
    List<Users> getAll();

    @Insert(onConflict = REPLACE)
    void insertUserNotesref(List<UsersNotesCrossref> userNotesCrossref);

    @Transaction
    @Query("SELECT * FROM Users")
    public List<UsersWithNotes> getUsersWithNotes();


}

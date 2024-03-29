package com.easydev.notesApp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.easydev.notesApp.Models.Notes;
import com.easydev.notesApp.Models.Relations.UsersNotesCrossref;
import com.easydev.notesApp.Models.Reminder;
import com.easydev.notesApp.Models.Users;

@Database(entities = {Notes.class, Users.class, Reminder.class, UsersNotesCrossref.class}, version = 9, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB database;
    private static String DATABASE_NAME = "NotesApp";

    public synchronized static RoomDB getInstance(Context context){
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract MainDAO mainDAO();
    public abstract UserDAO userDAO();
    public abstract ReminderDAO reminderDAO();

}

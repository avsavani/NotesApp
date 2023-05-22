package com.easydev.notesApp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reminder")
public class Reminder {
    @PrimaryKey(autoGenerate = true)
    public int reminderID = 0;

    @ColumnInfo(name = "note")
    String note = "";

    @ColumnInfo(name = "time")
    String time = "";

    @ColumnInfo(name = "date")
    String date = "";

    public int getID() {
        return reminderID;
    }

    public void setID(int ID) {
        this.reminderID = ID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

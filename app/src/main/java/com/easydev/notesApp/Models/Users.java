package com.easydev.notesApp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "users")
public class Users implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int usersID = 0;

    @ColumnInfo(name = "name")
    public String name = "";

    @ColumnInfo(name = "email")
    public String email = "";

    @ColumnInfo(name = "password")
    public String password = "";

    public Users(int id, String name, String email) {
        this.usersID =id;
        this.name = name;
        this.email = email;
    }

    public Users() {

    }

    public int getID() {
        return usersID;
    }

    public void setID(int ID) {
        this.usersID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

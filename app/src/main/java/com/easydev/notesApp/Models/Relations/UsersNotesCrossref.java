package com.easydev.notesApp.Models.Relations;

import androidx.room.Entity;

@Entity(primaryKeys = {"usersID","notesID"})
public class UsersNotesCrossref {
       public int usersID;
       public int notesID;

}

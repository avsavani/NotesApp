package com.easydev.notesApp.Models.Relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.easydev.notesApp.Models.Notes;
import com.easydev.notesApp.Models.Users;

import java.util.List;

public class NotesWithUsers {
    @Embedded
    public Notes notes;
    @Relation(
            parentColumn = "notesID",
            entityColumn = "usersID",
            associateBy = @Junction(UsersNotesCrossref.class)
    )
    public List<Users> users;
}

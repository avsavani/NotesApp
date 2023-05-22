package com.easydev.notesApp.Models.Relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.easydev.notesApp.Models.Notes;
import com.easydev.notesApp.Models.Users;

import java.util.List;

public class UsersWithNotes {
    @Embedded public Users user;
    @Relation(
            parentColumn = "usersID",
            entityColumn = "notesID",
            associateBy = @Junction(UsersNotesCrossref.class)
    )
    public List<Notes> notes;
}

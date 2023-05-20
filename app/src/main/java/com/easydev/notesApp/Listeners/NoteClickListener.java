package com.easydev.notesApp.Listeners;

import androidx.cardview.widget.CardView;

import com.easydev.notesApp.Models.Notes;

public interface NoteClickListener {
    void onClick(Notes notes);
    void onLongClick(Notes notes, CardView cardView);
}

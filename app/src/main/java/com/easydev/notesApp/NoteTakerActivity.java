package com.easydev.notesApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.easydev.notesApp.Database.RoomDB;
import com.easydev.notesApp.Models.Notes;
import com.easydev.notesApp.Models.Reminder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NoteTakerActivity extends AppCompatActivity {
    Toolbar toolbar_notes;
    ImageButton button_save, button_back;
    EditText editText_title, editText_notes;
    Notes note;
    Button button_set, button_date, button_time;
    String timeTonotify;
    Reminder reminder;
    RoomDB database;

    boolean isOldNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_taker);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black));

        editText_notes = findViewById(R.id.editText_notes);
        editText_title = findViewById(R.id.editText_title);
        button_save = findViewById(R.id.button_save);
        toolbar_notes = findViewById(R.id.toolbar_notes);
        button_back = findViewById(R.id.button_back);
        button_date = findViewById(R.id.btnDate);
        button_time = findViewById(R.id.btnTime);
        button_set = findViewById(R.id.button_set);
        database = RoomDB.getInstance(this);
        reminder = new Reminder();
        note = new Notes();

        try {
            note = (Notes) getIntent().getSerializableExtra("old_note");
            editText_title.setText(note.getTitle());
            editText_notes.setText(note.getNotes());
            isOldNote = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editText_title.getText().toString();
                String details = editText_notes.getText().toString();
                if (details.isEmpty()){
                    Toast.makeText(NoteTakerActivity.this, "Please add a note!", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm a");
                Date date = new Date();

                if (!isOldNote){
                    note = new Notes();
                }

                note.setTitle(title);
                note.setNotes(details);
                note.setDate(format.format(date));

                Intent intent = new Intent();
                intent.putExtra("note", note);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        button_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate();
            }                                        //when we click on the choose date button it calls the select date method
        });
        button_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTime();                                                                       //when we click on the choose time button it calls the select time method
            }
        });
        button_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editText_title.getText().toString().trim();                               //access the data form the input field
                String date = button_date.getText().toString().trim();                                 //access the date form the choose date button
                String time = button_time.getText().toString().trim();                                 //access the time form the choose time button

                if (title.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter text", Toast.LENGTH_SHORT).show();   //shows the toast if input field is empty
                } else {
                    if (time.equals("time") || date.equals("date")) {                                               //shows toast if date and time are not selected
                        Toast.makeText(getApplicationContext(), "Please select date and time", Toast.LENGTH_SHORT).show();
                    } else {
                        processinsert(title, date, time);

                    }
                }


            }
        });

    }
    private void selectDate() {                                                                     //this method performs the date picker task
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                button_date.setText(day + "-" + (month + 1) + "-" + year);                             //sets the selected date as test for button
            }
        }, year, month, day);
        datePickerDialog.show();
    }
    private void selectTime() {                                                                     //this method performs the time picker task
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeTonotify = i + ":" + i1;                                                        //temp variable to store the time to set alarm
                button_time.setText(FormatTime(i, i1));                                                //sets the button text as selected time
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }
    public String FormatTime(int hour, int minute) {                                                //this method converts the time into 12hr farmat and assigns am or pm

        String time;
        time = "";
        String formattedMinute;

        if (minute / 10 == 0) {
            formattedMinute = "0" + minute;
        } else {
            formattedMinute = "" + minute;
        }


        if (hour == 0) {
            time = "12" + ":" + formattedMinute + " AM";
        } else if (hour < 12) {
            time = hour + ":" + formattedMinute + " AM";
        } else if (hour == 12) {
            time = "12" + ":" + formattedMinute + " PM";
        } else {
            int temp = hour - 12;
            time = temp + ":" + formattedMinute + " PM";
        }
        return time;
    }
    private void processinsert(String title, String date, String time) {
        reminder = new Reminder();
        reminder.setDate(title);
        reminder.setTime(date);
        reminder.setNote(time);

        database.reminderDAO().insert(reminder);                  //inserts the title,date,time into sql lite database
        Toast.makeText(getApplicationContext(), "reminder set", Toast.LENGTH_SHORT).show();
    }

}
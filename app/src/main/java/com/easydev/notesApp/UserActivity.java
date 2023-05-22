package com.easydev.notesApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.easydev.notesApp.Database.RoomDB;
import com.easydev.notesApp.Models.Users;

public class UserActivity extends AppCompatActivity {

    TextView name,email;
    SharedPrefManager sharedPrefManager;
    RoomDB database;
    Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        database = RoomDB.getInstance(this);


        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        String username = sharedPrefManager.getUser().getName();
        name.setText("UN: "+username);
        user = database.userDAO().getEntry(username);
//        Toast.makeText(UserActivity.this, "val: "+ , Toast.LENGTH_SHORT).show();

        email.setText("email: "+user.email+" ID: "+user.usersID);

    }
}
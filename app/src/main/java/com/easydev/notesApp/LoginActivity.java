package com.easydev.notesApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.easydev.notesApp.Database.RoomDB;
import com.easydev.notesApp.Models.Users;

public class LoginActivity extends AppCompatActivity {

    RoomDB database;
    EditText password,name,email;
    Users user;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String LAYOUT = "layout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database = RoomDB.getInstance(this);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
//        email = findViewById(R.id.email);

        user = new Users();
    }
    public void signupBtn(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void loginBtn(View view){

        String getPass = password.getText().toString();
        String getName = name.getText().toString();
//        String getMail = email.getText().toString();
        if (getPass.isEmpty() || getName.isEmpty()){
            Toast.makeText(LoginActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        user = new Users();
//        user.setEmail(getMail);
        user.setName(getName);
        user.setPassword(getPass);

        if(database.userDAO().login(getName ,getPass)  ){

            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, NotesActivity.class);
            startActivity(intent);

            Log.d("test","password: "+getPass+" name: "+getName);
            finish();
        }else{
            Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}
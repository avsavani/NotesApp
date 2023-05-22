package com.easydev.notesApp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.easydev.notesApp.Models.Users;

public class SharedPrefManager {
    private static String SHARED_PREF_NAME="easydev";
    private SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor;

    public SharedPrefManager(Context context){
        this.context = context;
    }
    void SaveUser(Users users){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putInt("id",users.getID());
        editor.putString("name",users.getName());
        editor.putString("email", users.getEmail());
        editor.putBoolean("logged",true);
        editor.apply();

   }
   public boolean isLoggedIn(){
       sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
       return sharedPreferences.getBoolean("logged",false);
   }
   public Users getUser(){
       sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
       return new Users(sharedPreferences.getInt("id",-1),
               sharedPreferences.getString("name",null),
               sharedPreferences.getString("email",null));

   }
    public void logOut(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,0);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
        editor.apply();

    }

}

package com.example.june18.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPref {
    private Context context;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    public MyPref(Context context) {
        this.context = context;
        sharedPref = context.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    public void setName(String name) {
        editor.putString(KEY_NAME, name);
        editor.apply();
    }

    public String getName() {
        return sharedPref.getString(KEY_NAME, "User");
    }

    public void setEmail(String email) {
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public String getEmail() {
        return sharedPref.getString(KEY_EMAIL, "email");
    }

    public void setPassword(String password) {
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public String getPassword() {
        return sharedPref.getString(KEY_PASSWORD, "******");
    }
}


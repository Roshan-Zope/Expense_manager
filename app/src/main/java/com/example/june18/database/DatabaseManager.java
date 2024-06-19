package com.example.june18.database;

import android.content.Context;
import com.example.june18.models.Expense;

public class DatabaseManager {
    private DatabaseHelper db;

    public DatabaseManager(Context context) {
        db = new DatabaseHelper(context);
    }

    public void insertData(Expense expense) {
        db.insertData(expense);
    }
}

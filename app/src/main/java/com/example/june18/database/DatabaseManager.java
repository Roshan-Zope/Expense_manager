package com.example.june18.database;

import android.content.Context;
import android.util.Log;

import com.example.june18.models.Expense;

import java.util.List;

public class DatabaseManager {
    private DatabaseHelper db;

    public DatabaseManager(Context context) {
        db = new DatabaseHelper(context);
    }

    public void insertData(Expense expense) {
        Log.d("myTag", "in DatabaseManager class");
        db.insertData(expense);
    }

    public List<Expense> fetchData() {
        return db.fetchData();
    }

    public double getTotalAmount() {
        return db.getTotalAmount();
    }

    public void update(Expense expense) {db.update(expense);}

    public void delete(int id) {
        db.delete(id);
    }
}

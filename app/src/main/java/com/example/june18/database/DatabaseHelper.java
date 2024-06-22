package com.example.june18.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.june18.models.Expense;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "expenseManager";
    private static final String EXPENSE_TABLE_NAME = "expense";
    private static final String CATEGORY_TABLE_NAME = "category";
    private static final String EXPENSE_TABLE_EXPENSE_ID_COLUMN = "expenseId";
    private static final String EXPENSE_TABLE_CATEGORY_ID_COLUMN = "categoryId";
    private static final String EXPENSE_TABLE_CATEGORY_COLUMN = "category";
    private static final String EXPENSE_TABLE_AMOUNT_COLUMN = "amount";
    private static final String EXPENSE_TABLE_NOTE_COLUMN = "note";
    private static final String EXPENSE_TABLE_DATE_COLUMN = "date";
    private static final String CATEGORY_TABLE_CATEGORY_ID_COLUMN = "categoryId";
    private static final String CATEGORY_TABLE_CATEGORY_NAME_COLUMN = "category";
    private static final String EXPENSE_TABLE_SELECT_QUERY = "SELECT * FROM " + EXPENSE_TABLE_NAME;
    private static final String EXPENSE_TABLE_GET_TOTAL_AMOUNT_QUERY = "SELECT SUM(amount) FROM " + EXPENSE_TABLE_NAME;
    private static final String CREATE_EXPENSE_TABLE = "CREATE TABLE " + EXPENSE_TABLE_NAME +
            "( " +
            EXPENSE_TABLE_EXPENSE_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            EXPENSE_TABLE_CATEGORY_ID_COLUMN + " INTEGER," +
            EXPENSE_TABLE_CATEGORY_COLUMN + " TEXT," +
            EXPENSE_TABLE_AMOUNT_COLUMN + " REAL," +
            EXPENSE_TABLE_NOTE_COLUMN + " TEXT," +
            EXPENSE_TABLE_DATE_COLUMN + " TEXT"+ ")";
    private static final String CREATE_CATEGORY_TABLE = "CREATE TABLE " + CATEGORY_TABLE_NAME +
            "( " +
            CATEGORY_TABLE_CATEGORY_ID_COLUMN + " INTEGER," +
            CATEGORY_TABLE_CATEGORY_NAME_COLUMN + " TEXT" +
            ");";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EXPENSE_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertData(Expense expense) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EXPENSE_TABLE_CATEGORY_COLUMN, expense.getCategory());
        values.put(EXPENSE_TABLE_AMOUNT_COLUMN, expense.getAmount());
        values.put(EXPENSE_TABLE_DATE_COLUMN, expense.getDate());
        values.put(EXPENSE_TABLE_NOTE_COLUMN, expense.getNote());
        long newRowId = db.insert(EXPENSE_TABLE_NAME, null, values);

        if (newRowId == -1) Log.d("myTag", "data is not inserted");
        else Log.d("myTag", "data inserted");
        Log.d("myTag", "in DatabaseHelper class");
//        db.close(); // Close database connection
        return newRowId;
    }

    public List<Expense> fetchData() {
        List<Expense> expenses = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(EXPENSE_TABLE_SELECT_QUERY, null);

        if (cursor.moveToFirst()) {
            do {
                Expense expense = new Expense();
                expense.setExpenseId(cursor.getInt(cursor.getColumnIndexOrThrow(EXPENSE_TABLE_EXPENSE_ID_COLUMN)));
                expense.setCategory(cursor.getString(cursor.getColumnIndexOrThrow(EXPENSE_TABLE_CATEGORY_COLUMN)));
                expense.setAmount(cursor.getDouble(cursor.getColumnIndexOrThrow(EXPENSE_TABLE_AMOUNT_COLUMN)));
                expense.setDate(cursor.getString(cursor.getColumnIndexOrThrow(EXPENSE_TABLE_DATE_COLUMN)));
                expense.setNote(cursor.getString(cursor.getColumnIndexOrThrow(EXPENSE_TABLE_NOTE_COLUMN)));
                expenses.add(expense);
                Log.d("myTag", expense.toString());
            } while (cursor.moveToNext());
        }
        Log.d("myTag", "in fetch data method");

        return expenses;
    }

    public double getTotalAmount() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(EXPENSE_TABLE_GET_TOTAL_AMOUNT_QUERY, null);

        if (cursor.moveToNext()) {
            return cursor.getDouble(0);
        }
        return 0d;
    }

    public boolean update(Expense expense) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXPENSE_TABLE_CATEGORY_COLUMN, expense.getCategory());
        contentValues.put(EXPENSE_TABLE_AMOUNT_COLUMN, expense.getAmount());
        contentValues.put(EXPENSE_TABLE_NOTE_COLUMN, expense.getNote());
        contentValues.put(EXPENSE_TABLE_DATE_COLUMN, expense.getDate());

        return sqLiteDatabase.update(EXPENSE_TABLE_NAME, contentValues, EXPENSE_TABLE_EXPENSE_ID_COLUMN + " = ?", new String[]{String.valueOf(expense.getExpenseId())}) > 0;
    }

    public boolean delete(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(EXPENSE_TABLE_NAME, EXPENSE_TABLE_EXPENSE_ID_COLUMN + " = ?", new String[]{String.valueOf(id)}) > 0;
    }
}

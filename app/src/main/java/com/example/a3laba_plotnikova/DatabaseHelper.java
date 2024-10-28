package com.example.a3laba_plotnikova;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "students.db";
    private static final int DATABASE_VERSION = 2; // Увеличиваем версию

    public static final String TABLE_NAME = "classmates";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PATRONYMIC = "patronymic";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_SURNAME + " TEXT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_PATRONYMIC + " TEXT, " +
            COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" + ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
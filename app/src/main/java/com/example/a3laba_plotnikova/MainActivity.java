package com.example.a3laba_plotnikova;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        findViewById(R.id.button_view).setOnClickListener(v -> {
            Intent intent = new Intent(this, ViewActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.button_add).setOnClickListener(v -> addRecord());

        findViewById(R.id.button_update_last).setOnClickListener(v -> updateLastRecord());
    }

    private void addRecord() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, "Новая запись");
        db.insert(DatabaseHelper.TABLE_NAME, null, values);
    }

    private void updateLastRecord() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE " + DatabaseHelper.TABLE_NAME + " SET " +
                DatabaseHelper.COLUMN_NAME + "='Иванов Иван Иванович' WHERE " +
                DatabaseHelper.COLUMN_ID + "=(SELECT MAX(" + DatabaseHelper.COLUMN_ID + ") FROM " +
                DatabaseHelper.TABLE_NAME + ")");
    }
}
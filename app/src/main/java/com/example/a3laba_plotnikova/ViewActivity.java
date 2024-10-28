package com.example.a3laba_plotnikova;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ViewActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        textView = findViewById(R.id.textView);
        displayRecords();
    }

    @SuppressLint("Range")
    private void displayRecords() {
        SQLiteDatabase db = new DatabaseHelper(this).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME, null);
        StringBuilder data = new StringBuilder();

        int studentNumber = 1; // Счётчик студентов
        while (cursor.moveToNext()) {
            data.append("Студент ")
                    .append(studentNumber++) // Номер студента
                    .append(": ")
                    .append(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME))) // ФИО
                    .append(", ")
                    .append(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TIMESTAMP))) // Время добавления
                    .append("\n");
        }

        textView.setText(data.toString());
        cursor.close();
    }
}
package com.example.project;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLData;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    Button Bloginregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SET UP THE DATABASE
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();


        Bloginregister = (Button) findViewById(R.id.bloginregister);
        Bloginregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class);
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(i);
                startActivity(intent);

            }
        });

    }

    public void onClick(View view) {
        Intent i = new Intent(MainActivity.this, Doctors_available.class);
        startActivity(i);
    }
    public void onClick2(View view) {
        Intent i = new Intent(MainActivity.this, Services_offered.class);
        startActivity(i);
    }
}

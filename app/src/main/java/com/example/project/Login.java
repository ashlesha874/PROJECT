package com.example.project;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


import com.example.project.Doctor.Doctor;
import com.example.project.Patient.Patient;
import com.example.project.Staff_Member.Staff_Members;

public class Login extends AppCompatActivity {
    EditText username, password;
    String usernames, passwords;
    Button Btnregister, Btnlogin;
    DatabaseHelper dbh;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (EditText) findViewById(R.id.etusername);
        password = (EditText) findViewById(R.id.password);
        Btnregister = (Button) findViewById(R.id.bregister);
        Btnlogin = (Button) findViewById(R.id.blogin);
        dbh = new DatabaseHelper(this);

        Btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });

        Btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernames = username.getText().toString();
                passwords = password.getText().toString();

                Cursor y = dbh.checkduplicates_in_user_credentials(usernames, passwords, getResources().getString(R.string.user_credentials));

                if (y.moveToFirst()) {
                    String ut = y.getString(7);
                    Message.message(Login.this, "Welcome");

                    Bundle b = new Bundle();
                    b.putString("username", usernames);
                    b.putString("password", passwords);
                    b.putString("user_type", ut);

                    Intent i = new Intent();
                    if (ut.equals("Doctor")) {
                        i = new Intent(Login.this, Doctor.class);
                    } else if (ut.equals("Patient")) {
                        i = new Intent(Login.this, Patient.class);
                    } else if (ut.equals("Staff Member")) {
                        i = new Intent(Login.this, Staff_Members.class);
                    }


                    i.putExtras(b);
                    startActivity(i);
                } else {
                    Message.message(Login.this, "No Such User Exists");
                    Message.message(Login.this, "Please Register Your self");
                }

                y.close();
            }
        });
        dbh.close();
    }

}

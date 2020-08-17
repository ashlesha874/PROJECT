package com.example.project.Staff_Member;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.DatabaseHelper;
import com.example.project.Feedback;
import com.example.project.Personal_Info;
import com.example.project.R;


public class Staff_Members extends AppCompatActivity {
    String username, password, user_type;
    DatabaseHelper dbh;
    TextView sname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_member);

        dbh = new DatabaseHelper(this);
        sname = (TextView) findViewById(R.id.tv_s_name);


        Bundle bb = getIntent().getExtras();
        username = bb.getString("username");
        password = bb.getString("password");
        user_type = bb.getString("user_type");

        Cursor y = dbh.checkduplicates_in_user_credentials(username, password, getResources().getString(R.string.user_credentials));

        if (y.moveToFirst()) {
            String name = y.getString(1);
            sname.setText(name);
        }
    }

    public <Staff_Member> void onClick(View view) {

        Intent i;
        Bundle b = new Bundle();
        b.putString("username", username);
        b.putString("password", password);
        b.putString("user_type", user_type);

        switch (view.getId()) {
            case R.id.b_s_info:
                i = new Intent(Staff_Members.this, Personal_Info.class);
                break;
            case R.id.b_s_assigned_doctor:
                i = new Intent(Staff_Members.this, Assigned_Doctors.class);
                break;
            default:
                i = new Intent(Staff_Members.this, Feedback.class);
                break;
        }

        i.putExtras(b);
        startActivity(i);
    }

}

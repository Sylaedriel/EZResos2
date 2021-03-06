package com.example.inittowinit.ezresos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;


public class MainActivity extends AppCompatActivity
{

    private EditText fnameET;
    private EditText lnameET;
    private EditText emailET;

    private String fname;
    private String lname;
    private String email;

    public User currUser;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference("users");
    DatabaseReference classroomRef = database.getReference("classrooms");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        System.out.println("*********************here");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.fnameET = (EditText)this.findViewById(R.id.fnameTextBox);
        this.lnameET = (EditText)this.findViewById(R.id.lnameTextBox);
        this.emailET = (EditText)this.findViewById(R.id.emailTextBox);

        Random r = new Random();
        for(int i = 0; i < 20; i++)
        {
            DatabaseReference tempClassroom = classroomRef.push();
            Classroom clsrm = new Classroom("AL" + i, r.nextInt(20) + 45);
            tempClassroom.setValue(clsrm);
        }
    }

    public void continueAsGuestButtonPressed(View v)
    {
        this.fname = this.fnameET.getText().toString();
        this.lname = this.lnameET.getText().toString();
        this.email = this.emailET.getText().toString();


        createUser(fname, lname, email);
        if(currUser != null)
        {
        loadHomePage();
        }
    }

    public void createUser(String fname, String lname, String email)
    {
        if (fname != null && lname != null && email != null)
        {
            DatabaseReference tempUser = usersRef.push();
            currUser = new User(fname, lname, email);
            tempUser.setValue(currUser);
        }
    }

    public void loadHomePage()
    {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}

package com.example.multifunctionalfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //A small change
    /* Some Tries For User Profile Page
        username = (EditText) findViewById(R.id.editTextTextUsername);
        box1 = (CheckBox) findViewById(R.id.checkBox4);
        box1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = username.getText().toString();
                Toast.makeText(MainActivity.this,"Username is: " + username, Toast.LENGTH_SHORT );
            }
        });*/
}
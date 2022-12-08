package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.multifunctionalfitnessapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity( new Intent( MainActivity.this, LogInActivity.class ) );
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
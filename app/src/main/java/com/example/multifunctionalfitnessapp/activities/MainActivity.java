package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.multifunctionalfitnessapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_screen);

        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
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
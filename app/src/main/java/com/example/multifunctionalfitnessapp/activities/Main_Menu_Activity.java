package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.multifunctionalfitnessapp.R;

public class Main_Menu_Activity extends AppCompatActivity {

    Button findFitnessBuddy;
    Button myProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        findFitnessBuddy = findViewById(R.id.button3);
        findFitnessBuddy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_Menu_Activity.this, Fitness_Buddy_Found_Activity.class));
            }
        });
        myProfile = findViewById(R.id.button2);
        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_Menu_Activity.this, User_Profile_Activity.class));
            }
        });
    }

}
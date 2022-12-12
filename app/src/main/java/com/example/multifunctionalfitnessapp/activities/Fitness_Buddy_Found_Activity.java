package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.multifunctionalfitnessapp.NormalUser;
import com.example.multifunctionalfitnessapp.R;

public class Fitness_Buddy_Found_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_fitness_budy_1);
        displayFitnessBuddy();
    }

    public void displayFitnessBuddy() {
        NormalUser fitnessBuddy = new NormalUser("name", "surname", "username",
                "password", "phoneNumber", "email");
        String fullName = fitnessBuddy.getName() + fitnessBuddy.getSurname();
        String phoneNumber = fitnessBuddy.getPhoneNumber();
        // normally, we will reach day and hour from the database
        String date = "12.12";
        String hour = "17-18";
        ((TextView) findViewById(R.id.textView2)).setText(fullName);
        ((TextView) findViewById(R.id.textView3)).setText(phoneNumber);
        ((TextView) findViewById(R.id.textView4)).setText(date);
        ((TextView) findViewById(R.id.textView5)).setText(hour);
    }
}
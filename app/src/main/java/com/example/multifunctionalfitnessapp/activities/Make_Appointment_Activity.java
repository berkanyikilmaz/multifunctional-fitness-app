package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.multifunctionalfitnessapp.R;

public class Make_Appointment_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_appointment);
    }

    public void showFacilitiesToChoose() {
        // I should figure out how we can take the choice inside this method

        // this method will show facilities for the user to choose from
        // after the user has chosen 1, displayFacility method
        // will be used to display the weekly schedule of the chosen facility
        displayFacility();
    }

    public void displayFacility() {
        // this method will display the weekly schedule of the facility user chose
        // we can display it not as we do for displaying a facility to the owner
        // we better do it with colors saying available or not
        // for example; red for unavailable hours and white for the available ones
        
    }
}
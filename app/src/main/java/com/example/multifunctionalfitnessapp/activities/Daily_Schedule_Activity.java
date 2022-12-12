package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.multifunctionalfitnessapp.DailySchedule;
import com.example.multifunctionalfitnessapp.NormalUser;
import com.example.multifunctionalfitnessapp.PersonTimeInterval;
import com.example.multifunctionalfitnessapp.R;

public class Daily_Schedule_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_schedule);
        updateHours();
    }

    public void updateHours() {
        // we are working for normal user right now
        // we should look for every time interval 1 by 1
        // it goes from 8-9 to 18-19.
        // we should have a for loop controlling each and making the necassary udpates
        DailySchedule dailySchedule = new DailySchedule();
        // i will be the starting hour
        for (int i = 8; i <= 18; i++) {
            // getting the PersonTimeInterval object for the specific hour
            PersonTimeInterval interval = (PersonTimeInterval) dailySchedule.getInterval(i);
            if (interval.isAppointed) {
                // in this case the interval is appointed,
                // we should display the appointed facility
                String facilityName = interval.getAppointedFacility().getName();
                if (i == 8) {
                    // hour 1
                    ((TextView) findViewById(R.id.Hour1)).setText("Appointment at " + facilityName);
                }
                else if (i == 9) {
                    ((TextView) findViewById(R.id.Hour2)).setText("Appointment at " + facilityName);
                }
                else if (i == 10) {
                    ((TextView) findViewById(R.id.Hour3)).setText("Appointment at " + facilityName);
                }
                else if (i == 11) {
                    ((TextView) findViewById(R.id.Hour4)).setText("Appointment at " + facilityName);
                }
                else if (i == 12) {
                    ((TextView) findViewById(R.id.Hour5)).setText("Appointment at " + facilityName);
                }
                else if (i == 13) {
                    ((TextView) findViewById(R.id.Hour6)).setText("Appointment at " + facilityName);
                }
                else if (i == 14) {
                    ((TextView) findViewById(R.id.Hour7)).setText("Appointment at " + facilityName);
                }
                else if (i == 15) {
                    ((TextView) findViewById(R.id.Hour8)).setText("Appointment at " + facilityName);
                }
                else if (i == 16) {
                    ((TextView) findViewById(R.id.Hour9)).setText("Appointment at " + facilityName);
                }
                else if (i == 17) {
                    ((TextView) findViewById(R.id.Hour10)).setText("Appointment at " + facilityName);
                }
                else if (i == 18) {
                    ((TextView) findViewById(R.id.Hour11)).setText("Appointment at " + facilityName);
                }

            }
            else {
                // in this case the interval is not appointed,
                // therefore free
                if (i == 8) {
                    // hour 1
                    ((TextView) findViewById(R.id.Hour1)).setText("No appointment");
                }
                else if (i == 9) {
                    ((TextView) findViewById(R.id.Hour2)).setText("No appointment");
                }
                else if (i == 10) {
                    ((TextView) findViewById(R.id.Hour3)).setText("No appointment");
                }
                else if (i == 11) {
                    ((TextView) findViewById(R.id.Hour4)).setText("No appointment");
                }
                else if (i == 12) {
                    ((TextView) findViewById(R.id.Hour5)).setText("No appointment");
                }
                else if (i == 13) {
                    ((TextView) findViewById(R.id.Hour6)).setText("No appointment");
                }
                else if (i == 14) {
                    ((TextView) findViewById(R.id.Hour7)).setText("No appointment");
                }
                else if (i == 15) {
                    ((TextView) findViewById(R.id.Hour8)).setText("No appointment");
                }
                else if (i == 16) {
                    ((TextView) findViewById(R.id.Hour9)).setText("No appointment");
                }
                else if (i == 17) {
                    ((TextView) findViewById(R.id.Hour10)).setText("No appointment");
                }
                else if (i == 18) {
                    ((TextView) findViewById(R.id.Hour11)).setText("No appointment");
                }
            }
        }
    }

}
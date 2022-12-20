package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.multifunctionalfitnessapp.DailySchedule;
import com.example.multifunctionalfitnessapp.FacilityTimeInterval;
import com.example.multifunctionalfitnessapp.PersonTimeInterval;
import com.example.multifunctionalfitnessapp.R;

public class Facility_Daily_Schedule_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_schedule);
        updateHours();
    }

    public void updateHours() {
        DailySchedule dailySchedule = new DailySchedule();

        for (int i = 0; i <= 23; i++) {
            // getting the FacilityTimeInterval object for the specific hour
            FacilityTimeInterval interval = (FacilityTimeInterval) dailySchedule.getInterval(i);

            String occupancyRate = interval.getNoOfAppointedUser() + "/" + interval.quota;
            if (i == 0) {
                ((TextView) findViewById(R.id.Hour1)).setText(occupancyRate);
            }
            else if (i == 1) {
                ((TextView) findViewById(R.id.Hour2)).setText(occupancyRate);
            }
            else if (i == 2) {
                ((TextView) findViewById(R.id.Hour3)).setText(occupancyRate);
            }
            else if (i == 3) {
                ((TextView) findViewById(R.id.Hour4)).setText(occupancyRate);
            }
            else if (i == 4) {
                ((TextView) findViewById(R.id.Hour5)).setText(occupancyRate);
            }
            else if (i == 5) {
                ((TextView) findViewById(R.id.Hour6)).setText(occupancyRate);
            }
            else if (i == 6) {
                ((TextView) findViewById(R.id.Hour7)).setText(occupancyRate);
            }
            else if (i == 7) {
                ((TextView) findViewById(R.id.Hour8)).setText(occupancyRate);
            }
            else if (i == 8) {
                ((TextView) findViewById(R.id.Hour9)).setText(occupancyRate);
            }
            else if (i == 9) {
                ((TextView) findViewById(R.id.Hour10)).setText(occupancyRate);
            }
            else if (i == 10) {
                ((TextView) findViewById(R.id.Hour11)).setText(occupancyRate);
            }
            else if (i == 11) {
                ((TextView) findViewById(R.id.Hour12)).setText(occupancyRate);
            }
            else if (i == 12) {
                ((TextView) findViewById(R.id.Hour13)).setText(occupancyRate);
            }
            else if (i == 13) {
                ((TextView) findViewById(R.id.Hour14)).setText(occupancyRate);
            }
            else if (i == 14) {
                ((TextView) findViewById(R.id.Hour15)).setText(occupancyRate);
            }
            else if (i == 15) {
                ((TextView) findViewById(R.id.Hour16)).setText(occupancyRate);
            }
            else if (i == 16) {
                ((TextView) findViewById(R.id.Hour17)).setText(occupancyRate);
            }
            else if (i == 17) {
                ((TextView) findViewById(R.id.Hour18)).setText(occupancyRate);
            }
            else if (i == 18) {
                ((TextView) findViewById(R.id.Hour19)).setText(occupancyRate);
            }
            else if (i == 19) {
                ((TextView) findViewById(R.id.Hour20)).setText(occupancyRate);
            }
            else if (i == 20) {
                ((TextView) findViewById(R.id.Hour21)).setText(occupancyRate);
            }
            else if (i == 21) {
                ((TextView) findViewById(R.id.Hour22)).setText(occupancyRate);
            }
            else if (i == 22) {
                ((TextView) findViewById(R.id.Hour23)).setText(occupancyRate);
            }
            else if (i == 23) {
                ((TextView) findViewById(R.id.Hour24)).setText(occupancyRate);
            }
        }
    }
}
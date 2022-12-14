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

        for (int i = 8; i <= 18; i++) {
            // getting the FacilityTimeInterval object for the specific hour
            FacilityTimeInterval interval = (FacilityTimeInterval) dailySchedule.getInterval(i);

            String occupancyRate = interval.getNoOfAppointedUser() + "/" + interval.getQuota();
            if (i == 0) {
                ((Button) findViewById(R.id.Hour1)).setText(occupancyRate);
            }
            else if (i == 1) {
                ((Button) findViewById(R.id.Hour2)).setText(occupancyRate);
            }
            else if (i == 2) {
                ((Button) findViewById(R.id.Hour3)).setText(occupancyRate);
            }
            else if (i == 3) {
                ((Button) findViewById(R.id.Hour4)).setText(occupancyRate);
            }
            else if (i == 4) {
                ((Button) findViewById(R.id.Hour5)).setText(occupancyRate);
            }
            else if (i == 5) {
                ((Button) findViewById(R.id.Hour6)).setText(occupancyRate);
            }
            else if (i == 6) {
                ((Button) findViewById(R.id.Hour7)).setText(occupancyRate);
            }
            else if (i == 7) {
                ((Button) findViewById(R.id.Hour8)).setText(occupancyRate);
            }
            else if (i == 8) {
                ((Button) findViewById(R.id.Hour9)).setText(occupancyRate);
            }
            else if (i == 9) {
                ((Button) findViewById(R.id.Hour10)).setText(occupancyRate);
            }
            else if (i == 10) {
                ((Button) findViewById(R.id.Hour11)).setText(occupancyRate);
            }
            else if (i == 11) {
                ((Button) findViewById(R.id.Hour12)).setText(occupancyRate);
            }
            else if (i == 12) {
                ((Button) findViewById(R.id.Hour13)).setText(occupancyRate);
            }
            else if (i == 13) {
                ((Button) findViewById(R.id.Hour14)).setText(occupancyRate);
            }
            else if (i == 14) {
                ((Button) findViewById(R.id.Hour15)).setText(occupancyRate);
            }
            else if (i == 15) {
                ((Button) findViewById(R.id.Hour16)).setText(occupancyRate);
            }
            else if (i == 16) {
                ((Button) findViewById(R.id.Hour17)).setText(occupancyRate);
            }
            else if (i == 17) {
                ((Button) findViewById(R.id.Hour18)).setText(occupancyRate);
            }
            else if (i == 18) {
                ((Button) findViewById(R.id.Hour19)).setText(occupancyRate);
            }
            else if (i == 19) {
                ((Button) findViewById(R.id.Hour20)).setText(occupancyRate);
            }
            else if (i == 20) {
                ((Button) findViewById(R.id.Hour21)).setText(occupancyRate);
            }
            else if (i == 21) {
                ((Button) findViewById(R.id.Hour22)).setText(occupancyRate);
            }
            else if (i == 22) {
                ((Button) findViewById(R.id.Hour23)).setText(occupancyRate);
            }
            else if (i == 23) {
                ((Button) findViewById(R.id.Hour24)).setText(occupancyRate);
            }
        }
    }
}
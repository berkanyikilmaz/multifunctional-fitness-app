package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
            if (i == 8) {
                // hour 1
                ((TextView) findViewById(R.id.Hour1)).setText(occupancyRate);
            }
            else if (i == 9) {
                ((TextView) findViewById(R.id.Hour2)).setText(occupancyRate);
            }
            else if (i == 10) {
                ((TextView) findViewById(R.id.Hour3)).setText(occupancyRate);
            }
            else if (i == 11) {
                ((TextView) findViewById(R.id.Hour4)).setText(occupancyRate);
            }
            else if (i == 12) {
                ((TextView) findViewById(R.id.Hour5)).setText(occupancyRate);
            }
            else if (i == 13) {
                ((TextView) findViewById(R.id.Hour6)).setText(occupancyRate);
            }
            else if (i == 14) {
                ((TextView) findViewById(R.id.Hour7)).setText(occupancyRate);
            }
            else if (i == 15) {
                ((TextView) findViewById(R.id.Hour8)).setText(occupancyRate);
            }
            else if (i == 16) {
                ((TextView) findViewById(R.id.Hour9)).setText(occupancyRate);
            }
            else if (i == 17) {
                ((TextView) findViewById(R.id.Hour10)).setText(occupancyRate);
            }
            else if (i == 18) {
                ((TextView) findViewById(R.id.Hour11)).setText(occupancyRate);
            }
        }
    }
}
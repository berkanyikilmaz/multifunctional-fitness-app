package com.example.multifunctionalfitnessapp.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multifunctionalfitnessapp.Constants;
import com.example.multifunctionalfitnessapp.PersonTimeInterval;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.Schedule;
import com.example.multifunctionalfitnessapp.ScheduleHelper;

public class Create_Schedule_Activity extends AppCompatActivity {

    TableLayout dailySchedule;
    View createScheduleView;
    int selectedDay = 0;

    Schedule userSchedule; //FOR NORMAL USER

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_schedule);

        userSchedule = Schedule.createEmptyUserSchedule();

        registerDailyScheduleLayout();
    }

    public void registerDailyScheduleLayout() {
        createScheduleView = findViewById(R.id.createScheduleDailySchedule);
        dailySchedule = createScheduleView.findViewById(R.id.dailyScheduleTableLayout);

        registerDaysDropdown();
        ScheduleHelper.updateCreateScheduleValues(dailySchedule, userSchedule.fullSchedule[selectedDay]);

        for (int n = 1; n < dailySchedule.getChildCount(); n++) {
            TableRow row = (TableRow)dailySchedule.getChildAt(n);
            TextView name = (TextView) row.getChildAt(1);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int rowIndex = ScheduleHelper.getRowIndex(row, view.getContext());

                    PersonTimeInterval interval = (PersonTimeInterval) userSchedule.fullSchedule[selectedDay].fullDailySchedule[rowIndex];
                    interval.isAvailable = !interval.isAvailable;
                    Log.d(rowIndex + "", interval.isAvailable + "");

                    ScheduleHelper.updateCreateScheduleValues(dailySchedule, userSchedule.fullSchedule[selectedDay]);
                }
            });
        }

        /*ScheduleHelper.setScheduleLayout(this, normalUserMainMenuScheduleView, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("" + name.getText().toString(), "is clicked");
                // OPEN PERSON TIME INTERVAL PANEL
                    if(!name.getBackground().equals(Color.RED))
                        name.setBackgroundColor(Color.RED);
                    else
                        name.setBackgroundColor(Color.WHITE);
            }
        });*/
    }

    public void registerDaysDropdown() {
        AutoCompleteTextView dropdown = createScheduleView.findViewById(R.id.daysAutoCompleteTextView);
        ArrayAdapter<String> adapterItems = new ArrayAdapter<String>(this, R.layout.dropdown_item, Constants.DAYS);
        dropdown.setAdapter(adapterItems);

        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("days", adapterView.getItemAtPosition(i).toString() + " at position " + i + " is selected.");
                selectedDay = i;
                ScheduleHelper.updateCreateScheduleValues(dailySchedule, userSchedule.fullSchedule[selectedDay]);
            }
        });
    }
}
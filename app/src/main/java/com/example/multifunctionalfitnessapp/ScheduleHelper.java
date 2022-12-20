package com.example.multifunctionalfitnessapp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ScheduleHelper {

    public static String replaceRowText = "com.example.multifunctionalfitnessapp:id/row";

    public static void setScheduleLayout(Context ctx, View scheduleLayout, View.OnClickListener rowListener) {
        TableLayout dailySchedule = scheduleLayout.findViewById(R.id.dailyScheduleTableLayout);

        registerDaysDropdown(ctx, scheduleLayout);

        for (int n = 1; n < dailySchedule.getChildCount(); n++) {
            TableRow row = (TableRow)dailySchedule.getChildAt(n);
            TextView name = (TextView) row.getChildAt(1);

            row.setOnClickListener(rowListener);
        }
    }

    public static void registerDaysDropdown(Context ctx, View scheduleLayout) {
        AutoCompleteTextView dropdown = scheduleLayout.findViewById(R.id.daysAutoCompleteTextView);
        ArrayAdapter<String> adapterItems = new ArrayAdapter<String>(ctx, R.layout.dropdown_item, Constants.DAYS);
        dropdown.setAdapter(adapterItems);

        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("days", adapterView.getItemAtPosition(i).toString() + " at position " + i + " is selected.");
            }
        });
    }

    public static int getRowIndex(TableRow row, Context ctx) {
        String rowId = ctx.getResources().getResourceName(row.getId()).replace(replaceRowText,"");
        int rowIndex = Integer.parseInt(rowId) - 1;

        return rowIndex;
    }

    public static void updateMakeAppointmentScheduleValues(TableLayout scheduleTable, DailySchedule userDailySchedule, DailySchedule facilityDailySchedule, DailySchedule commonSchedule) {
        // takes parameters (TableLayout scheduleTable, DailySchedule userDailySchedule, DailySchedule facilityDailySchedule)

        for (int n = 1; n < scheduleTable.getChildCount(); n++) {
            TableRow row = (TableRow)scheduleTable.getChildAt(n);
            TextView name = (TextView)row.getChildAt(1);

            if (((FacilityTimeInterval)(commonSchedule.fullDailySchedule[n-1])).isSelected) {
                name.setBackgroundColor(Color.GREEN);
                name.setText("SELECTED");
            }
            else if (((PersonTimeInterval)(userDailySchedule.fullDailySchedule[n-1])).isAvailable &&
                    !((FacilityTimeInterval)(facilityDailySchedule.fullDailySchedule[n-1])).isFull()) {
                name.setBackgroundColor(Color.DKGRAY);
                name.setText("");
            }
            else if (!((PersonTimeInterval)(userDailySchedule.fullDailySchedule[n-1])).isAvailable &&
                    !((FacilityTimeInterval)(facilityDailySchedule.fullDailySchedule[n-1])).isFull()) {
                name.setBackgroundColor(Color.YELLOW);
                name.setText("");
            }
            else if (((PersonTimeInterval)(userDailySchedule.fullDailySchedule[n-1])).isAvailable &&
                    ((FacilityTimeInterval)(facilityDailySchedule.fullDailySchedule[n-1])).isFull()) {
                name.setBackgroundColor(Color.RED);
                name.setText("quota is full");
            }
            else {

                name.setBackgroundColor(Color.RED);
                name.setText("UNAVAILABLE");
            }
        }
    }

    public static void updateCreateScheduleValues(TableLayout scheduleTable, DailySchedule dailySchedule) {

        for (int n = 1; n < scheduleTable.getChildCount(); n++) {
            TableRow row = (TableRow)scheduleTable.getChildAt(n);
            TextView name = (TextView)row.getChildAt(1);

            if (((PersonTimeInterval)(dailySchedule.fullDailySchedule[n-1])).isAvailable) {
                name.setBackgroundColor(Color.WHITE);
                name.setText("");
            }
            else {
                name.setBackgroundColor(Color.RED);
                name.setText("UNAVAILABLE");
            }
        }
    }

    public static void updateCreateFacilityScheduleValues(TableLayout scheduleTable, DailySchedule dailySchedule) {
        for (int n = 1; n < scheduleTable.getChildCount(); n++) {
            TableRow row = (TableRow)scheduleTable.getChildAt(n);
            TextView name = (TextView)row.getChildAt(1);

            FacilityTimeInterval interval = ((FacilityTimeInterval)(dailySchedule.fullDailySchedule[n-1]));

            if (interval.isSelected) {
                name.setBackgroundColor(Color.GREEN);
                name.setText("SELECTED");
            }
            else if (interval.quota == 0) {
                name.setBackgroundColor(Color.WHITE);
                name.setText("Quota: 0");
            }
            else {
                name.setBackgroundColor(Color.YELLOW);
                name.setText("Quota: " + interval.quota);
            }
        }
    }

    public static void updateEditFacilityScheduleValues(TableLayout scheduleTable, DailySchedule dailySchedule) {
        for (int n = 1; n < scheduleTable.getChildCount(); n++) {
            TableRow row = (TableRow)scheduleTable.getChildAt(n);
            TextView name = (TextView)row.getChildAt(1);

            FacilityTimeInterval interval = ((FacilityTimeInterval)(dailySchedule.fullDailySchedule[n-1]));

            if (interval.isSelected) {
                name.setBackgroundColor(Color.GREEN);
                name.setText("SELECTED");
            }
            else if (interval.quota == 0) {
                name.setBackgroundColor(Color.WHITE);
                name.setText("Quota: 0");
            }
            else {
                name.setBackgroundColor(Color.YELLOW);
                name.setText("Quota: " + interval.quota + "/ Users: " + interval.getNoOfAppointedUser());
            }
        }
    }

    public static void updateUserMainMenuScheduleValues(TableLayout scheduleTable, int dailyScheduleIndex) {
        UserData userData = UserData.getInstance();

        DatabaseReference dailyScheduleRef = FirebaseManager.getInstance().databaseRef.child("users").child(userData.username).child("schedule").child(dailyScheduleIndex+"");

        for (int i = 0; i < 24; i++) {
            TableRow row = (TableRow)scheduleTable.getChildAt(i+1);
            TextView name = (TextView)row.getChildAt(1);

            DatabaseReference timeIntervalRef = dailyScheduleRef.child(i+"");

            timeIntervalRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    PersonTimeInterval interval = snapshot.getValue(PersonTimeInterval.class);

                    if (interval.isAppointed) {
                        name.setBackgroundColor(Color.BLUE);
                        name.setText("Appointed at " + interval.appointedFacility.getName());
                    }
                    else if (!interval.isAvailable) {
                        name.setBackgroundColor(Color.RED);
                        name.setText("UNAVAILABLE");
                    }
                    else {
                        name.setBackgroundColor(Color.WHITE);
                        name.setText("");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public static void updateUserMainMenuScheduleValuesFromUser(TableLayout scheduleTable, int dailyScheduleIndex, NormalUser normalUser) {
        for (int i = 0; i < 24; i++) {
            TableRow row = (TableRow)scheduleTable.getChildAt(i+1);
            TextView name = (TextView)row.getChildAt(1);

            DailySchedule dailySchedule = normalUser.schedule.fullSchedule[dailyScheduleIndex];
            PersonTimeInterval interval = (PersonTimeInterval) dailySchedule.fullDailySchedule[i];

            if (interval.isAppointed) {
                name.setBackgroundColor(Color.BLUE);
                name.setText("Appointed at " + interval.appointedFacility.getName());
            }
            else if (!interval.isAvailable) {
                name.setBackgroundColor(Color.RED);
                name.setText("UNAVAILABLE");
            }
            else {
                name.setBackgroundColor(Color.WHITE);
                name.setText("");
            }
        }
    }
}

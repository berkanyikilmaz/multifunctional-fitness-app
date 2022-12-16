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

    public static int getRowIndex(TableRow row, Context ctx) {
        String rowId = ctx.getResources().getResourceName(row.getId()).replace(replaceRowText,"");
        int rowIndex = Integer.parseInt(rowId) - 1;

        return rowIndex;
    }
}

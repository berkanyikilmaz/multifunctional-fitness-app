package com.example.multifunctionalfitnessapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multifunctionalfitnessapp.Constants;
import com.example.multifunctionalfitnessapp.Facility;
import com.example.multifunctionalfitnessapp.FacilityTimeInterval;
import com.example.multifunctionalfitnessapp.FirebaseManager;
import com.example.multifunctionalfitnessapp.PersonTimeInterval;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.Schedule;
import com.example.multifunctionalfitnessapp.ScheduleHelper;
import com.example.multifunctionalfitnessapp.UserData;
import com.google.firebase.database.DatabaseReference;

public class Create_Facility_Activity extends AppCompatActivity {

    FirebaseManager firebaseManager = FirebaseManager.getInstance();
    UserData userData;

    EditText facilityName;
    EditText quotaEditText;
    Button confirmQuota;
    Button createFacilityButton;

    TableLayout dailySchedule;
    View createFacilityView;
    int selectedDay = 0;

    Schedule facilitySchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_facility);

        facilitySchedule = Schedule.createEmptyFacilitySchedule();

        userData = UserData.getInstance();

        registerFacilityNameAndQuota();
        registerDailyScheduleLayout();
        registerConfirmButton();
        registerContinueButton();
    }

    public void registerFacilityNameAndQuota() {
        facilityName = findViewById(R.id.editTextFacilityName);
        quotaEditText = findViewById(R.id.editQuota);
    }

    public void registerConfirmButton() {
        confirmQuota = findViewById(R.id.confirmButton);

        confirmQuota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newQuota = Integer.parseInt(quotaEditText.getText().toString());

                for (int i = 0; i < facilitySchedule.fullSchedule.length; i++) {
                    for (int j = 0; j < (facilitySchedule.fullSchedule[i]).fullDailySchedule.length; j++) {
                        FacilityTimeInterval interval = (FacilityTimeInterval) facilitySchedule.fullSchedule[i].fullDailySchedule[j];

                        if (interval.isSelected) {
                            interval.setQuota(newQuota);
                            interval.isSelected = false;
                        }
                    }
                }

                ScheduleHelper.updateCreateFacilityScheduleValues(dailySchedule, facilitySchedule.fullSchedule[selectedDay]);
            }
        });
    }

    public void registerContinueButton() {
        createFacilityButton = findViewById(R.id.createButton);
        DatabaseReference userRef = firebaseManager.databaseRef.child("users").child(userData.username);

        createFacilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String facilityNameText = facilityName.getText().toString();

                /*Facility newFacility = new Facility(null);
                newFacility.setName(facilityNameText);*/

                DatabaseReference facilityRef = userRef.child("facilities").child(facilityNameText);

                for (int i = 0; i < facilitySchedule.fullSchedule.length; i++) {
                    for (int j = 0; j < (facilitySchedule.fullSchedule[i]).fullDailySchedule.length; j++) {

                        facilityRef.child("schedule").child(i+"").child(j+"").setValue((facilitySchedule.fullSchedule[i]).fullDailySchedule[j]);
                    }
                }

                startActivity( new Intent( Create_Facility_Activity.this, Facility_Owner_Main_Menu_Activity.class ) );
            }
        });
    }

    public void registerDailyScheduleLayout() {
        createFacilityView = findViewById(R.id.createFacilityScheduleLayout);
        dailySchedule = createFacilityView.findViewById(R.id.dailyScheduleTableLayout);

        registerDaysDropdown();
        ScheduleHelper.updateCreateFacilityScheduleValues(dailySchedule, facilitySchedule.fullSchedule[selectedDay]);

        for (int n = 1; n < dailySchedule.getChildCount(); n++) {
            TableRow row = (TableRow)dailySchedule.getChildAt(n);
            TextView name = (TextView) row.getChildAt(1);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int rowIndex = ScheduleHelper.getRowIndex(row, view.getContext());

                    FacilityTimeInterval interval = (FacilityTimeInterval) facilitySchedule.fullSchedule[selectedDay].fullDailySchedule[rowIndex];
                    interval.isSelected = !interval.isSelected;
                    Log.d(rowIndex + "", interval.isSelected + "");

                    ScheduleHelper.updateCreateFacilityScheduleValues(dailySchedule, facilitySchedule.fullSchedule[selectedDay]);
                }
            });
        }
    }

    public void registerDaysDropdown() {
        AutoCompleteTextView dropdown = createFacilityView.findViewById(R.id.daysAutoCompleteTextView);
        ArrayAdapter<String> adapterItems = new ArrayAdapter<String>(this, R.layout.dropdown_item, Constants.DAYS);
        dropdown.setAdapter(adapterItems);

        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("days", adapterView.getItemAtPosition(i).toString() + " at position " + i + " is selected.");
                selectedDay = i;
                ScheduleHelper.updateCreateFacilityScheduleValues(dailySchedule, facilitySchedule.fullSchedule[selectedDay]);
            }
        });
    }
}

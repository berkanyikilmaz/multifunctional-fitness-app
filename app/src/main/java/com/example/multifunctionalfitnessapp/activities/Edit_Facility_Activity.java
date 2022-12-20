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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.multifunctionalfitnessapp.Constants;
import com.example.multifunctionalfitnessapp.Facility;
import com.example.multifunctionalfitnessapp.FacilityTimeInterval;
import com.example.multifunctionalfitnessapp.FirebaseManager;
import com.example.multifunctionalfitnessapp.OnGetDataListener;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.Schedule;
import com.example.multifunctionalfitnessapp.ScheduleHelper;
import com.example.multifunctionalfitnessapp.UserData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

public class Edit_Facility_Activity extends AppCompatActivity {

    FirebaseManager firebaseManager = FirebaseManager.getInstance();
    UserData userData;

    EditText facilityName;
    EditText quotaEditText;
    Button confirmButton;
    Button continueButton;

    TableLayout dailySchedule;
    View editFacilityView;
    Facility facility;
    Schedule facilitySchedule;
    int selectedDay = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_facility);

        userData = UserData.getInstance();
        facility = userData.facility;
        facilitySchedule = facility.getSchedule();

        registerFacilityNameAndQuota();
        registerDailyScheduleLayout();
        registerConfirmButton();
        registerContinueButton();

    }

    public void registerContinueButton(){
        continueButton = (Button) findViewById(R.id.editFacilityContinueButton);
        //DatabaseReference userRef = firebaseManager.databaseRef.child("users").child(userData.username);
        //DatabaseReference facilitiesRef = firebaseManager.databaseRef.child("facilities");

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*String facilityNameText = facilityName.getText().toString();

                userRef.child("facilities").child(facilityNameText).setValue("");
                DatabaseReference facilityRef = facilitiesRef.child(facilityNameText);

                for (int i = 0; i < facilitySchedule.fullSchedule.length; i++) {
                    for (int j = 0; j < (facilitySchedule.fullSchedule[i]).fullDailySchedule.length; j++) {
                        facilityRef.child("schedule").child(i+"").child(j+"").child("");
                    }
                }*/
                startActivity( new Intent( Edit_Facility_Activity.this, Facility_Owner_Main_Menu_Activity.class ) );
            }
        });
    }

    public void registerConfirmButton(){
        confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newQuota = Integer.parseInt(quotaEditText.getText().toString());

                for (int i = 0; i < facilitySchedule.fullSchedule.length; i++) {
                    for (int j = 0; j < (facilitySchedule.fullSchedule[i]).fullDailySchedule.length; j++) {
                        FacilityTimeInterval interval = (FacilityTimeInterval) facilitySchedule.fullSchedule[i].fullDailySchedule[j];

                        if (interval.isSelected) {
                            if (interval.getNoOfAppointedUser() <= newQuota) {
                                interval.quota = newQuota;
                                interval.isSelected = false;
                                firebaseManager.updateFacilityQuota(facility,interval,interval.quota);
                            }
                        }
                    }
                }

                ScheduleHelper.updateEditFacilityScheduleValues(dailySchedule, facilitySchedule.fullSchedule[selectedDay]);
            }
        });
}
    public void registerFacilityNameAndQuota() {
        facilityName = findViewById(R.id.editTextFacilityName);
        facilityName.setText(userData.facility.getName().toString());
        quotaEditText = findViewById(R.id.editQuota);
    }
    public void registerDailyScheduleLayout() {
        editFacilityView = findViewById(R.id.editFacilityDailySchedule);
        dailySchedule = editFacilityView.findViewById(R.id.dailyScheduleTableLayout);

        registerDaysDropdown();
        ScheduleHelper.updateEditFacilityScheduleValues(dailySchedule, facilitySchedule.fullSchedule[selectedDay]);

        for (int n = 1; n < dailySchedule.getChildCount(); n++) {
            TableRow row = (TableRow)dailySchedule.getChildAt(n);
            TextView name = (TextView) row.getChildAt(1);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int rowIndex = ScheduleHelper.getRowIndex(row, view.getContext());

                    FacilityTimeInterval interval = (FacilityTimeInterval) facilitySchedule.fullSchedule[selectedDay].fullDailySchedule[rowIndex];

                    interval.isSelected = !interval.isSelected;
                    Log.d(rowIndex + "", interval.dailySchedule.day + "");

                    ScheduleHelper.updateEditFacilityScheduleValues(dailySchedule, facilitySchedule.fullSchedule[selectedDay]);
                }
            });
        }
    }

    public void registerDaysDropdown() {
        AutoCompleteTextView dropdown = editFacilityView.findViewById(R.id.daysAutoCompleteTextView);
        ArrayAdapter<String> adapterItems = new ArrayAdapter<String>(this, R.layout.dropdown_item, Constants.DAYS);
        dropdown.setAdapter(adapterItems);

        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("days", adapterView.getItemAtPosition(i).toString() + " at position " + i + " is selected.");
                selectedDay = i;
                ScheduleHelper.updateEditFacilityScheduleValues(dailySchedule, facilitySchedule.fullSchedule[selectedDay]);
            }
        });
    }


}

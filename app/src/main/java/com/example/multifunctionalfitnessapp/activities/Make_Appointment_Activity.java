package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.multifunctionalfitnessapp.Constants;
import com.example.multifunctionalfitnessapp.Facility;
import com.example.multifunctionalfitnessapp.FacilityTimeInterval;
import com.example.multifunctionalfitnessapp.FirebaseManager;
import com.example.multifunctionalfitnessapp.NormalUser;
import com.example.multifunctionalfitnessapp.OnGetDataListener;
import com.example.multifunctionalfitnessapp.PersonTimeInterval;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.Schedule;
import com.example.multifunctionalfitnessapp.ScheduleHelper;
import com.example.multifunctionalfitnessapp.UserData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Make_Appointment_Activity extends AppCompatActivity {

    FirebaseManager firebaseManager = FirebaseManager.getInstance();

    UserData userData;
    NormalUser normalUser;

    TableLayout dailySchedule;
    View makeAppointmentScheduleView;
    int selectedDay = 0;

    ArrayList<Facility> allFacilities;
    AutoCompleteTextView autoCompleteTextView2my;
    ArrayAdapter<String> adapterItems;

    int selectedFacility = 0;

    Schedule commonSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        allFacilities = new ArrayList<>();

        userData = UserData.getInstance();
        normalUser = userData.normalUser;

        firebaseManager.getFacilitiesSnapshot(new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                commonSchedule = Schedule.createEmptyFacilitySchedule();

                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    Facility newFacility = new Facility();
                    newFacility.setName(childSnapshot.getKey());
                    Log.d("facName", childSnapshot.getKey());
                    userData.setFacilitySchedule(newFacility, childSnapshot);
                    allFacilities.add(newFacility);
                }

                setContentView(R.layout.make_appointment);

                registerMakeAnAppointmentScheduleLayout();
                registerAppointButton();
                registerFacilityDropdown();
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFailure() {

            }
        });
    }

    public void registerMakeAnAppointmentScheduleLayout() {

        makeAppointmentScheduleView = findViewById(R.id.makeAppointmentDailySchedule);
        dailySchedule = makeAppointmentScheduleView.findViewById(R.id.dailyScheduleTableLayout);

        registerDaysDropdown();
        ScheduleHelper.updateMakeAppointmentScheduleValues(
                dailySchedule,
                normalUser.getSchedule().fullSchedule[selectedDay],
                allFacilities.get(selectedFacility).getSchedule().fullSchedule[selectedDay],
                commonSchedule.fullSchedule[selectedDay]);

        for (int n = 1; n < dailySchedule.getChildCount(); n++) {
            TableRow row = (TableRow)dailySchedule.getChildAt(n);
            TextView name = (TextView) row.getChildAt(1);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int rowIndex = ScheduleHelper.getRowIndex(row, view.getContext());

                    ScheduleHelper.updateMakeAppointmentScheduleValues(
                            dailySchedule,
                            normalUser.getSchedule().fullSchedule[selectedDay],
                            allFacilities.get(selectedFacility).getSchedule().fullSchedule[selectedDay],
                            commonSchedule.fullSchedule[selectedDay]);

                    ColorDrawable nameColor = (ColorDrawable) name.getBackground();
                    int color = nameColor.getColor();
                    if (color == Color.YELLOW) {
                        FacilityTimeInterval interval = ((FacilityTimeInterval)(commonSchedule.fullSchedule[selectedDay].fullDailySchedule[rowIndex]));
                        //interval.isSelected = true;
                        interval.setSelected(true);
                        name.setBackgroundColor(Color.GREEN);
                        name.setText("SELECTED");
                        Log.d("facN", allFacilities.get(selectedFacility).getName());
                    }
                    else if (color == Color.GREEN) {
                        FacilityTimeInterval interval = ((FacilityTimeInterval)(commonSchedule.fullSchedule[selectedDay].fullDailySchedule[rowIndex]));
                        //interval.isSelected = false;
                        interval.setSelected(false);
                        name.setBackgroundColor(Color.YELLOW);
                        name.setText("");
                        Log.d("facN", allFacilities.get(selectedFacility).getName());
                    }
                }
            });
        }
    }

    public void registerDaysDropdown() {
        AutoCompleteTextView dropdown = makeAppointmentScheduleView.findViewById(R.id.daysAutoCompleteTextView);
        ArrayAdapter<String> adapterItems = new ArrayAdapter<String>(this, R.layout.dropdown_item, Constants.DAYS);
        dropdown.setAdapter(adapterItems);

        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("days", adapterView.getItemAtPosition(i).toString() + " at position " + i + " is selected.");
                selectedDay = i;

                ScheduleHelper.updateMakeAppointmentScheduleValues(
                        dailySchedule,
                        normalUser.getSchedule().fullSchedule[selectedDay],
                        allFacilities.get(selectedFacility).getSchedule().fullSchedule[selectedDay],
                        commonSchedule.fullSchedule[selectedDay]);
            }
        });
    }


    public void registerAppointButton() {

        Button appointButton = findViewById(R.id.appointConfirmButton);
        DatabaseReference userRef = firebaseManager.databaseRef.child("users").child(userData.username);

        Facility facility = allFacilities.get(selectedFacility);

        appointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("clicked", "clicked");
                Log.d("facN", allFacilities.get(selectedFacility).getName());
                // Add appointment to user's schedule
                // Add appointment to the facility and change the quota
                for (int day = 0; day < 7; day++) {
                    for (int hour = 0; hour < 24; hour++) {
                        /*
                        // If color is yellow
                        // add appointment to the user
                        // add appointment to the facility,
                        // use methods defined in facility class to increase quota and make necessary changes
                        // */
                        PersonTimeInterval userInterval = (PersonTimeInterval)normalUser.getSchedule().fullSchedule[day].fullDailySchedule[hour];
                        FacilityTimeInterval facilityInterval = (FacilityTimeInterval)allFacilities.get(selectedFacility).getSchedule().fullSchedule[day].fullDailySchedule[hour];
                        FacilityTimeInterval commonInterval = (FacilityTimeInterval)commonSchedule.fullSchedule[day].fullDailySchedule[hour];

                        if (commonInterval.isSelected() == true) {
                            commonInterval.setSelected(false);

                            userInterval.addAppointment(facility);
                            facilityInterval.addAppointment(normalUser);

                            firebaseManager.appointmentDatabaseUpdate(normalUser, allFacilities.get(selectedFacility), true, day, hour);
                        }
                    }
                }

                startActivity( new Intent( Make_Appointment_Activity.this, User_Main_Menu_Activity.class ) );
            }
        });
    }

    public void registerFacilityDropdown() {
        String[] facilityNames = new String[allFacilities.size()];

        for (int i = 0; i < allFacilities.size(); i++) {
            facilityNames[i] = allFacilities.get(i).getName();
        }

        autoCompleteTextView2my = findViewById(R.id.autoCompleteTextView2);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item_test, facilityNames);

        autoCompleteTextView2my.setAdapter(adapterItems);
        autoCompleteTextView2my.setText(adapterItems.getItem(0), false);

        autoCompleteTextView2my.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
                selectedFacility = i;

                commonSchedule = Schedule.createEmptyFacilitySchedule();

                ScheduleHelper.updateMakeAppointmentScheduleValues(
                        dailySchedule,
                        normalUser.getSchedule().fullSchedule[selectedDay],
                        allFacilities.get(selectedFacility).getSchedule().fullSchedule[selectedDay],
                        commonSchedule.fullSchedule[selectedDay]);
            }
        });

        /*
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

         */
    }
}
package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.multifunctionalfitnessapp.FacilityOwner;
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

    Schedule userSchedule; // FOR THE USER
    Schedule facilitySchedule; //FOR THE CHOSEN FACILITY

    ArrayList<Facility> allFacilities;
    AutoCompleteTextView autoCompleteTextView2my;
    ArrayAdapter<String> adapterItems;

    int selectedFacility = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        allFacilities = new ArrayList<>();

        userData = UserData.getInstance();
        normalUser = userData.normalUser;

        firebaseManager.getFacilitiesSnapshot(new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    Facility newFacility = new Facility();
                    newFacility.setName(childSnapshot.getKey());

                    Schedule facilitySchedule = Schedule.createEmptyFacilitySchedule();

                    for (int day = 0; day < 7; day++) {
                        for (int hour = 0; hour < 24; hour++) {
                            FacilityTimeInterval interval = childSnapshot.child("schedule").child(day+"").child(hour+"").getValue(FacilityTimeInterval.class);
                            facilitySchedule.fullSchedule[day].fullDailySchedule[hour] = interval;
                        }
                    }

                    newFacility.setSchedule(facilitySchedule);
                    allFacilities.add(newFacility);
                }

                setContentView(R.layout.make_appointment);

                //registerFacilitiesToChoose();
                registerMakeAnAppointmentScheduleLayout();
                //registerAppointButton();
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

    public void registerFacilitiesToChoose() {

    }

    public void registerMakeAnAppointmentScheduleLayout() {

        makeAppointmentScheduleView = findViewById(R.id.makeAppointmentDailySchedule);
        dailySchedule = makeAppointmentScheduleView.findViewById(R.id.dailyScheduleTableLayout);

        registerDaysDropdown();
        ScheduleHelper.updateMakeAppointmentScheduleValues(
                dailySchedule,
                normalUser.getSchedule().fullSchedule[selectedDay],
                allFacilities.get(selectedFacility).getSchedule().fullSchedule[selectedDay]);

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

                    ScheduleHelper.updateMakeAppointmentScheduleValues(
                            dailySchedule,
                            normalUser.getSchedule().fullSchedule[selectedDay],
                            allFacilities.get(selectedFacility).getSchedule().fullSchedule[selectedDay]);
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
                        allFacilities.get(selectedFacility).getSchedule().fullSchedule[selectedDay]);
            }
        });
    }


    public void registerAppointButton() {

        Button appointButton = (Button) findViewById(R.id.makeAppointmentButton);
        DatabaseReference userRef = firebaseManager.databaseRef.child("users").child(userData.username);

        appointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add appointment to user's schedule
                // Add appointment to the facility and change the quota
                for (int i = 0; i < facilitySchedule.fullSchedule.length; i++) {
                    for (int j = 0; j < (facilitySchedule.fullSchedule[i]).fullDailySchedule.length; j++) {
                         /*
                        // If color is yellow
                        // add appointment to the user
                        // add appointment to the facility,
                        // use methods defined in facility class to increase quota and make necessary changes
                        // */
                        userRef.child("schedule").child(i+"").child(j+"").setValue((facilitySchedule.fullSchedule[i]).fullDailySchedule[j]);
                    }
                }
                for (int i = 0; i < userSchedule.fullSchedule.length; i++) {
                    for (int j = 0; j < (userSchedule.fullSchedule[i]).fullDailySchedule.length; j++) {

                        userRef.child("schedule").child(i+"").child(j+"").setValue((userSchedule.fullSchedule[i]).fullDailySchedule[j]);
                    }
                }
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

                ScheduleHelper.updateMakeAppointmentScheduleValues(
                        dailySchedule,
                        normalUser.getSchedule().fullSchedule[selectedDay],
                        allFacilities.get(selectedFacility).getSchedule().fullSchedule[selectedDay]);
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
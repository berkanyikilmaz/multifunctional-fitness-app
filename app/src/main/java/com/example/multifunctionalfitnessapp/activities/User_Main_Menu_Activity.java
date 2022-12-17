package com.example.multifunctionalfitnessapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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

import com.example.multifunctionalfitnessapp.Constants;
import com.example.multifunctionalfitnessapp.FirebaseManager;
import com.example.multifunctionalfitnessapp.NormalUser;
import com.example.multifunctionalfitnessapp.OnGetDataListener;
import com.example.multifunctionalfitnessapp.PersonTimeInterval;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.Schedule;
import com.example.multifunctionalfitnessapp.ScheduleHelper;
import com.example.multifunctionalfitnessapp.User;
import com.example.multifunctionalfitnessapp.UserData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class User_Main_Menu_Activity extends AppCompatActivity {

    FirebaseManager firebaseManager = FirebaseManager.getInstance();

    Button findFitnessBuddy;
    Button myProfile;
    Button makeAnAppointment;

    TableLayout dailySchedule;
    View normalUserMainMenuScheduleView;
    int selectedDay = 0;

    UserData userData;
    NormalUser normalUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_user_main_menu);

        userData = UserData.getInstance();

        firebaseManager.getNormalUserSnapshot(userData.username, new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                userData.setNormalUserFromSnapshot(snapshot);

                Log.d("name", userData.normalUser.getName());
                normalUser = userData.normalUser;

                registerFindFitnessBuddyButton();
                registerMyProfileButton();
                registerMakeAnAppointmentButton();
                registerDailyScheduleLayout();
                registerLogoutButton();
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFailure() {

            }
        });
    }

    public void getNormalUser(final OnGetDataListener listener) {
        listener.onStart();
        DatabaseReference ref = firebaseManager.databaseRef.child("users").child(userData.username);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listener.onSuccess(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onFailure();
            }
        });
    }

    public void registerFindFitnessBuddyButton() {
        findFitnessBuddy = findViewById(R.id.findFitnessBuddyButton);
        findFitnessBuddy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(User_Main_Menu_Activity.this, Fitness_Buddy_Found_Activity.class));
            }
        });
    }

    public void registerMyProfileButton() {
        myProfile = findViewById(R.id.profileButton);
        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(User_Main_Menu_Activity.this, User_Profile_Activity.class));
            }
        });
    }

    public void registerMakeAnAppointmentButton() {
        makeAnAppointment = findViewById(R.id.makeAppointmentButton);
        makeAnAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(User_Main_Menu_Activity.this, Make_Appointment_Activity.class));
            }
        });
    }

    public void registerLogoutButton() {

    }

    public void registerDailyScheduleLayout() {
        normalUserMainMenuScheduleView = findViewById(R.id.normalUserMainMenuSchedule);
        dailySchedule = normalUserMainMenuScheduleView.findViewById(R.id.dailyScheduleTableLayout);

        registerDaysDropdown();
        ScheduleHelper.updateUserMainMenuScheduleValuesFromUser(dailySchedule, selectedDay, normalUser);

        for (int n = 1; n < dailySchedule.getChildCount(); n++) {
            TableRow row = (TableRow)dailySchedule.getChildAt(n);
            TextView name = (TextView) row.getChildAt(1);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //OPEN TIME INTERVAL PANELS FOR THE CLICKED TIME INTERVAL
                }
            });
        }
    }

    public void registerDaysDropdown() {
        AutoCompleteTextView dropdown = normalUserMainMenuScheduleView.findViewById(R.id.daysAutoCompleteTextView);
        ArrayAdapter<String> adapterItems = new ArrayAdapter<String>(this, R.layout.dropdown_item, Constants.DAYS);
        dropdown.setAdapter(adapterItems);

        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("days", adapterView.getItemAtPosition(i).toString() + " at position " + i + " is selected.");
                selectedDay = i;
                ScheduleHelper.updateUserMainMenuScheduleValuesFromUser(dailySchedule, selectedDay, normalUser);
            }
        });
    }
}
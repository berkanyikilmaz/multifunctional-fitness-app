package com.example.multifunctionalfitnessapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.example.multifunctionalfitnessapp.Facility;
import com.example.multifunctionalfitnessapp.FacilityTimeInterval;
import com.example.multifunctionalfitnessapp.PersonTimeInterval;
import com.example.multifunctionalfitnessapp.RemoveDialog;
import com.example.multifunctionalfitnessapp.FirebaseManager;
import com.example.multifunctionalfitnessapp.NormalUser;
import com.example.multifunctionalfitnessapp.OnGetDataListener;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.ScheduleHelper;
import com.example.multifunctionalfitnessapp.User;
import com.example.multifunctionalfitnessapp.UserData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class User_Main_Menu_Activity extends AppCompatActivity implements RemoveDialog.RemoveDialogListener {

    FirebaseManager firebaseManager = FirebaseManager.getInstance();

    Button findFitnessBuddy;
    Button myProfile;
    Button makeAnAppointment;
    Button logoutButton;
    TextView welcomeNameTextView;

    TableLayout dailySchedule;
    View normalUserMainMenuScheduleView;
    int selectedDay = 0;

    UserData userData;
    NormalUser normalUser;

    Facility facilityToEdit;
    PersonTimeInterval intervalToEdit;
    int editedDay;
    int editedHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_user_main_menu);

        userData = UserData.getInstance();

        firebaseManager.getCompleteSnapshot(new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                userData.setNormalUserFromSnapshot(snapshot);

                Log.d("name", userData.normalUser.getName());
                normalUser = userData.normalUser;

                updateWelcomeNameTitle();
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

    private void updateWelcomeNameTitle(){
        welcomeNameTextView = findViewById(R.id.welcomeNameTitle);
        welcomeNameTextView.setText("Welcome " + normalUser.getName().toUpperCase(Locale.ROOT) + "!");
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
                startActivity(new Intent(User_Main_Menu_Activity.this, Choose_Time_Interval_Menu.class));
            }
        });
    }

    public void registerMyProfileButton() {
        myProfile = findViewById(R.id.profileButton);
        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(User_Main_Menu_Activity.this, User_Profile_Activity2.class));
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
        logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(User_Main_Menu_Activity.this, LogInActivity.class));
            }
        });
    }

    public void registerDailyScheduleLayout() {
        normalUserMainMenuScheduleView = findViewById(R.id.normalUserMainMenuSchedule);
        dailySchedule = normalUserMainMenuScheduleView.findViewById(R.id.dailyScheduleTableLayout);

        registerDaysDropdown();
        ScheduleHelper.updateUserMainMenuScheduleValuesFromUser(dailySchedule, selectedDay, normalUser);

        for (int n = 1; n < dailySchedule.getChildCount(); n++) {
            TableRow row = (TableRow) dailySchedule.getChildAt(n);
            TextView name = (TextView) row.getChildAt(1);

            row.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int rowIndex = ScheduleHelper.getRowIndex(row, view.getContext());

                    PersonTimeInterval interval = (PersonTimeInterval) normalUser.schedule.fullSchedule[selectedDay].fullDailySchedule[rowIndex];
                    intervalToEdit = interval;
                    editedDay = selectedDay;
                    editedHour = rowIndex;

                    ColorDrawable nameColor = (ColorDrawable) name.getBackground();
                    int color = nameColor.getColor();
                    if ((color == (Color.RED))){
                        name.setBackgroundColor(Color.WHITE);
                        name.setText("");

                        interval.isAvailable = true;
                        firebaseManager.updateUserSchedule(normalUser,selectedDay,rowIndex,"isAvailable", true);
                    }
                    else if(color == (Color.BLUE)) {
                        facilityToEdit = interval.appointedFacility;
                        openRemoveDialog(name);
                    }
                    else{
                        name.setBackgroundColor(Color.RED);
                        name.setText("UNAVAILABLE");

                        interval.isAvailable = false;
                        firebaseManager.updateUserSchedule(normalUser,selectedDay,rowIndex,"isAvailable", false);
                    }
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

    public void openRemoveDialog(TextView name){
        RemoveDialog dialog = new RemoveDialog(name, intervalToEdit, User_Main_Menu_Activity.this);
        dialog.show(getSupportFragmentManager(), "dialog");
    }


    public void applyRemoval(TextView name) {
        name.setBackgroundColor(Color.WHITE);
        name.setText("");

        Log.d("name",intervalToEdit.appointedFacility.getName());

        DatabaseReference userIntervalRef = firebaseManager.databaseRef.child("users").child(normalUser.getUsername()).child("schedule").child(editedDay+"").child(editedHour+"");
        userIntervalRef.child("isAppointed").setValue(false);
        userIntervalRef.child("isAvailable").setValue(true);
        userIntervalRef.child("appointedFacility").setValue(null);

        DatabaseReference facilityIntervalRef = firebaseManager.databaseRef.child("facilities").child(facilityToEdit.getName()).child("schedule").child(editedDay+"").child(editedHour+"");
        facilityIntervalRef.child("appointedUsers").child(normalUser.getUsername()).setValue(null);
    }

    public void findFitnessBuddy(PersonTimeInterval interval, final OnGetDataListener listener) {
        listener.onStart();

        firebaseManager.getCompleteSnapshot(new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                DataSnapshot facilitiesSnapshot = snapshot.child("facilities");
                DataSnapshot usersSnapshot = snapshot.child("users");

                DataSnapshot facilitySnapshot = facilitiesSnapshot.child(interval.appointedFacility.getName());
                DataSnapshot intervalSnapshot = facilitySnapshot.child("schedule").child(interval.dailySchedule.day+"").child(interval.getStartingHour() + "");

                for (DataSnapshot appointedUserSnapshot : intervalSnapshot.child("appointedUsers").getChildren()) {
                    String username = appointedUserSnapshot.getKey();
                    DataSnapshot userIntervalSnapshot = usersSnapshot.child(username).child("schedule").child(interval.dailySchedule.day+"").child(interval.getStartingHour() + "");

                    boolean wantsFitnessBuddy = userIntervalSnapshot.child("wantsFitnessBuddy").getValue(boolean.class);
                    if (wantsFitnessBuddy && intervalToEdit.wantsFitnessBuddy && !username.equals(normalUser.getUsername())) {
                        intervalToEdit.wantsFitnessBuddy = false;

                        NormalUser fitnessBuddy = new NormalUser();
                        fitnessBuddy.setUsername(username);
                        interval.fitnessBuddy = fitnessBuddy;

                        DatabaseReference normalUserIntervalRef = firebaseManager.databaseRef.child("users").child(normalUser.getUsername()).child("schedule").child(interval.dailySchedule.day+"").child(interval.getStartingHour()+"");
                        DatabaseReference fitnessBuddyIntervalRef = firebaseManager.databaseRef.child("users").child(username).child("schedule").child(interval.dailySchedule.day+"").child(interval.getStartingHour()+"");

                        normalUserIntervalRef.child("wantsFitnessBuddy").setValue(false);
                        normalUserIntervalRef.child("fitnessBuddy").setValue(fitnessBuddy.getUsername());
                        fitnessBuddyIntervalRef.child("wantsFitnessBuddy").setValue(false);
                        fitnessBuddyIntervalRef.child("fitnessBuddy").setValue(normalUser.getUsername());
                        listener.onSuccess(null);
                        return;
                    }
                }

                listener.onFailure();
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFailure() {

            }
        });


    }
}
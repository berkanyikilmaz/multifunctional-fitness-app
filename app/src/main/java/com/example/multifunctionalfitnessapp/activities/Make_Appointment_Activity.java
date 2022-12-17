package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.example.multifunctionalfitnessapp.Facility;
import com.example.multifunctionalfitnessapp.FacilityOwner;
import com.example.multifunctionalfitnessapp.FirebaseManager;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.Schedule;
import com.example.multifunctionalfitnessapp.UserData;

public class Make_Appointment_Activity extends AppCompatActivity {

    FirebaseManager firebaseManager = FirebaseManager.getInstance();
    UserData userData;

    TableLayout dailySchedule;
    View MakeAppointmentScheduleView;
    int selectedDay = 0;

    Schedule facilitySchedule; //FOR FACILITY CHOSEN

    Facility chosenFacility = new Facility(new FacilityOwner("","","","","",""));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_appointment);

        facilitySchedule = Schedule.createEmptyFacilitySchedule();
        userData = UserData.getInstance();

        registerFacilitiesToChoose();
        registerMakeAnAppointmentScheduleLayout();
        registerAppointButton();
    }

    public void registerFacilitiesToChoose() {

        
    }

    public void registerMakeAnAppointmentScheduleLayout() {


        
    }

    public void registerAppointButton() {

    }

    public void registerFacilitiesDropdown() {
        
    }
}
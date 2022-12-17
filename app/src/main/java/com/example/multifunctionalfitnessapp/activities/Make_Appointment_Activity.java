package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.multifunctionalfitnessapp.Constants;
import com.example.multifunctionalfitnessapp.Facility;
import com.example.multifunctionalfitnessapp.FacilityOwner;
import com.example.multifunctionalfitnessapp.FirebaseManager;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.Schedule;
import com.example.multifunctionalfitnessapp.ScheduleHelper;
import com.example.multifunctionalfitnessapp.UserData;

public class Make_Appointment_Activity extends AppCompatActivity {

    FirebaseManager firebaseManager = FirebaseManager.getInstance();
    UserData userData;

    TableLayout dailySchedule;
    View MakeAppointmentScheduleView;
    int selectedDay = 0;

    Schedule facilitySchedule; //FOR FACILITY CHOSEN

    Facility chosenFacility = new Facility(new FacilityOwner("","","","","",""));
    String[] days = new String[]{"facility1", "facility2","facility3",};
    AutoCompleteTextView autoCompleteTextView2my;
    ArrayAdapter<String> adapterItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_appointment);

        facilitySchedule = Schedule.createEmptyFacilitySchedule();
        userData = UserData.getInstance();

        registerFacilitiesToChoose();
        registerMakeAnAppointmentScheduleLayout();
        registerAppointButton();
        registerFacilityDropdown();
    }

    public void registerFacilitiesToChoose() {

        
    }

    public void registerMakeAnAppointmentScheduleLayout() {


        
    }

    public void registerAppointButton() {

    }

    public void registerFacilityDropdown() {
        autoCompleteTextView2my = findViewById(R.id.autoCompleteTextView2);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item_test,days);

        autoCompleteTextView2my.setAdapter(adapterItems);

        autoCompleteTextView2my.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
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
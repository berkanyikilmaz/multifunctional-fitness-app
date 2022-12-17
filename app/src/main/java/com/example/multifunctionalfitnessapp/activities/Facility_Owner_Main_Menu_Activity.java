package com.example.multifunctionalfitnessapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multifunctionalfitnessapp.Facility;
import com.example.multifunctionalfitnessapp.FacilityOwner;
import com.example.multifunctionalfitnessapp.R;

public class Facility_Owner_Main_Menu_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    FacilityOwner facilityOwner = new FacilityOwner("Ege", "Fitness", "egefitness", "***","05052","ege@gmail.com");
    Facility f1 = new Facility(facilityOwner);
    Facility f2 = new Facility(facilityOwner);
    Facility f3 = new Facility(facilityOwner);
    Facility f4 = new Facility(facilityOwner);
    Facility f5 = new Facility(facilityOwner);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facility_owner_main_menu);

        recyclerView = (RecyclerView) findViewById(R.id.facilityContainer);
        logoutButton();
        profileButton();
        createButton();

        FacilityContainerAdapter facilityContainerAdapter = new FacilityContainerAdapter(Facility_Owner_Main_Menu_Activity.this, facilityOwner );
        recyclerView.setAdapter(facilityContainerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void logoutButton() {
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( Facility_Owner_Main_Menu_Activity.this, LogInActivity.class ) );
                Log.d("logoutButton", " is clicked!");
            }
        });
    }

    private void profileButton() {
        Button profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( Facility_Owner_Main_Menu_Activity.this, User_Profile_Activity.class ) );
                Log.d("profileButton", " is clicked!");
            }
        });
    }

    private void createButton() {
        Button profileButton = findViewById(R.id.createButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( Facility_Owner_Main_Menu_Activity.this, Create_Facility_Activity.class ) );
                Log.d("createButton", " is clicked!");
            }
        });
    }


}

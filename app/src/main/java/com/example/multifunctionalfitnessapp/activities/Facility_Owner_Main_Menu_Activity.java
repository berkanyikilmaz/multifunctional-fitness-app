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
import com.example.multifunctionalfitnessapp.RecyclerViewInterface;

public class Facility_Owner_Main_Menu_Activity extends AppCompatActivity implements RecyclerViewInterface {

    RecyclerView recyclerView;
    FacilityOwner facilityOwner = new FacilityOwner("Ege", "Fitness", "egefitness", "***","05052","ege@gmail.com");
    Facility f1 = new Facility(facilityOwner);
    Facility f2 = new Facility(facilityOwner);
    Facility f3 = new Facility(facilityOwner);
    Facility f4 = new Facility(facilityOwner);
    Facility f5 = new Facility(facilityOwner);

    FacilityContainerAdapter facilityContainerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facility_owner_main_menu);

        recyclerView = (RecyclerView) findViewById(R.id.facilityContainer);
        logoutButton();
        profileButton();
        createButton();

        facilityContainerAdapter = new FacilityContainerAdapter(Facility_Owner_Main_Menu_Activity.this, facilityOwner, this );
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


    @Override
    public void onItemClick(int position) {
        startActivity( new Intent(Facility_Owner_Main_Menu_Activity.this, Edit_Facility_Activity.class));
    }

    @Override
    public void onItemLongClick(int position) {
        startActivity( new Intent(Facility_Owner_Main_Menu_Activity.this, Delete_Facility_Activity.class));
        facilityOwner.getFacilities().remove(position);
        facilityContainerAdapter.notifyItemRemoved(position);
        //We can immediately delete a facility without notification here deleting activity call but if we want to ask again we should do it in delete facility activity but
        // I don't know how to reach adapter outside this class?
    }
}

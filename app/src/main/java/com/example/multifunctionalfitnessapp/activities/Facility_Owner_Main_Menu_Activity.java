package com.example.multifunctionalfitnessapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multifunctionalfitnessapp.Facility;
import com.example.multifunctionalfitnessapp.FacilityOwner;
import com.example.multifunctionalfitnessapp.FirebaseManager;
import com.example.multifunctionalfitnessapp.OnGetDataListener;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.RecyclerViewInterface;
import com.example.multifunctionalfitnessapp.UserData;
import com.google.firebase.database.DataSnapshot;

import java.util.Locale;

public class Facility_Owner_Main_Menu_Activity extends AppCompatActivity implements RecyclerViewInterface {

    FirebaseManager firebaseManager = FirebaseManager.getInstance();

    RecyclerView recyclerView;

    FacilityContainerAdapter facilityContainerAdapter;
    TextView welcomeNameTextView;

    UserData userData;
    FacilityOwner facilityOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userData = UserData.getInstance();
        firebaseManager.getCompleteSnapshot(new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                userData.setFacilityOwnerFromDatabase(snapshot);
                facilityOwner = userData.facilityOwner;

                Log.d("ownerName", facilityOwner.getUsername());

                setContentView(R.layout.facility_owner_main_menu);

                recyclerView = (RecyclerView) findViewById(R.id.facilityContainer);
                logoutButton();
                profileButton();
                createButton();
                updateWelcomeNameTitle();

                facilityContainerAdapter = new FacilityContainerAdapter(Facility_Owner_Main_Menu_Activity.this, facilityOwner, Facility_Owner_Main_Menu_Activity.this );
                recyclerView.setAdapter(facilityContainerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Facility_Owner_Main_Menu_Activity.this));
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
        welcomeNameTextView.setText("Welcome " + facilityOwner.getName().toUpperCase(Locale.ROOT) + "!");
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
                startActivity( new Intent( Facility_Owner_Main_Menu_Activity.this, User_Profile_Activity2.class ) );
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
        userData.facility = facilityOwner.getFacilities().get(position);
        startActivity( new Intent(Facility_Owner_Main_Menu_Activity.this, Edit_Facility_Activity.class));
    }

    @Override
    public void onItemLongClick(int position) {
        Facility facilityToRemove = facilityOwner.getFacilities().get(position);
        Log.d("facName", facilityToRemove.getName());

        firebaseManager.deleteFacility(facilityOwner.getUsername(), facilityToRemove.getName(), new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                //facilityOwner.getFacilities().remove(position);
                facilityOwner.deleteFacility(position);
                facilityContainerAdapter.notifyItemRemoved(position);
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

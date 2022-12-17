package com.example.multifunctionalfitnessapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class Facility_Owner_Main_Menu_Activity extends AppCompatActivity implements RecyclerViewInterface {

    FirebaseManager firebaseManager = FirebaseManager.getInstance();

    RecyclerView recyclerView;
    FacilityOwner facilityOwner = new FacilityOwner("Ege", "Fitness", "egefitness", "***","05052","ege@gmail.com");
    Facility f1 = new Facility(facilityOwner);
    Facility f2 = new Facility(facilityOwner);
    Facility f3 = new Facility(facilityOwner);
    Facility f4 = new Facility(facilityOwner);
    Facility f5 = new Facility(facilityOwner);

    FacilityContainerAdapter facilityContainerAdapter;

    UserData userData;
    FacilityOwner testFacilityOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facility_owner_main_menu);

        userData = UserData.getInstance();
        firebaseManager.getUserSnapshot(userData.username, new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                userData.setFacilityOwnerFromDatabase(snapshot);
                testFacilityOwner = userData.facilityOwner;

                Log.d("ownerName", testFacilityOwner.getUsername());

                recyclerView = (RecyclerView) findViewById(R.id.facilityContainer);
                logoutButton();
                profileButton();
                createButton();

                facilityContainerAdapter = new FacilityContainerAdapter(Facility_Owner_Main_Menu_Activity.this, testFacilityOwner, Facility_Owner_Main_Menu_Activity.this );
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
        Facility facilityToRemove = testFacilityOwner.getFacilities().get(position);
        Log.d("facName", facilityToRemove.getName());

        firebaseManager.deleteFacility(testFacilityOwner.getUsername(), facilityToRemove.getName(), new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                testFacilityOwner.getFacilities().remove(position);
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

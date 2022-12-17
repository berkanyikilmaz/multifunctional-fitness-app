package com.example.multifunctionalfitnessapp;

import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserData {

    FirebaseManager firebaseManager = FirebaseManager.getInstance();

    public static UserData userData = null;

    public NormalUser normalUser;
    public FacilityOwner facilityOwner;

    public String username;
    public String name;

    public UserData() {
        username = null;
        name = null;
        normalUser = new NormalUser();
        facilityOwner = new FacilityOwner();
    }

    public static UserData getInstance() {
        if (userData == null) {
            userData = new UserData();
        }
        return userData;
    }

    public void login(String username) {
        this.username = username;
    }

    public void setNormalUserName(String name) {
        this.name = name;
    }

    public void setNormalUserFromSnapshot(DataSnapshot snapshot) {
        String name = snapshot.child("name").getValue(String.class);
        String surname = snapshot.child("surname").getValue(String.class);
        String username = snapshot.child("username").getValue(String.class);
        String password = snapshot.child("password").getValue(String.class);
        String phoneNo = snapshot.child("phoneNumber").getValue(String.class);
        String email = snapshot.child("email").getValue(String.class);

        userData.normalUser.setName(name);
        userData.normalUser.setSurname(surname);
        userData.normalUser.setUsername(username);
        userData.normalUser.setPassword(password);
        userData.normalUser.setPhoneNumber(phoneNo);
        userData.normalUser.setEmail(email);

        Schedule userSchedule = Schedule.createEmptyUserSchedule();

        for (int day = 0; day < 7; day++) {
            for (int hour = 0; hour < 24; hour++) {
                PersonTimeInterval interval = snapshot.child("schedule").child(day+"").child(hour+"").getValue(PersonTimeInterval.class);
                userSchedule.fullSchedule[day].fullDailySchedule[hour] = interval;
            }
        }

        normalUser.schedule = userSchedule;
    }

    //this method uses complete snapshot
    public void setFacilityOwnerFromDatabase(DataSnapshot snapshot) {
        DataSnapshot userSnapshot = snapshot.child("users").child(userData.username);

        String name = userSnapshot.child("name").getValue(String.class);
        String surname = userSnapshot.child("surname").getValue(String.class);
        String username = userSnapshot.child("username").getValue(String.class);
        String password = userSnapshot.child("password").getValue(String.class);
        String phoneNo = userSnapshot.child("phoneNumber").getValue(String.class);
        String email = userSnapshot.child("email").getValue(String.class);

        facilityOwner.setName(name);
        facilityOwner.setSurname(surname);
        facilityOwner.setUsername(username);
        facilityOwner.setPassword(password);
        facilityOwner.setPhoneNumber(phoneNo);
        facilityOwner.setEmail(email);

        facilityOwner.facilities = new ArrayList<Facility>();

        for(DataSnapshot childFacility : userSnapshot.child("facilities").getChildren()) {
            DataSnapshot facilitySnapshot = snapshot.child("facilities").child(childFacility.getKey());

            Facility newFacility = new Facility(facilityOwner);
            newFacility.setName(facilitySnapshot.getKey());

            Schedule facilitySchedule = Schedule.createEmptyFacilitySchedule();

            for (int day = 0; day < 7; day++) {
                for (int hour = 0; hour < 24; hour++) {
                    FacilityTimeInterval interval = facilitySnapshot.child("schedule").child(day+"").child(hour+"").getValue(FacilityTimeInterval.class);
                    facilitySchedule.fullSchedule[day].fullDailySchedule[hour] = interval;
                }
            }

            newFacility.setSchedule(facilitySchedule);
        }
    }

    /*public void setFacilities() {

    }*/



    public NormalUser getNormalUser() {
        return normalUser;
    }
}

package com.example.multifunctionalfitnessapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class FirebaseManager {

    private static FirebaseManager firebaseManager = null;

    public String databaseURL = "https://multifuntional-fitness-app-default-rtdb.europe-west1.firebasedatabase.app/";
    public DatabaseReference databaseRef;

    public FirebaseManager() {
        databaseRef = FirebaseDatabase.getInstance(databaseURL).getReference();
    }

    public static FirebaseManager getInstance() {
        if (firebaseManager == null) {
            firebaseManager = new FirebaseManager();
        }
        return firebaseManager;
    }

    public void sendUserDataToDatabase(User user) {
        databaseRef.child("users").child(user.getUsername()).child("userType").setValue(user.getUserType());
        databaseRef.child("users").child(user.getUsername()).child("name").setValue(user.getName());
        databaseRef.child("users").child(user.getUsername()).child("surname").setValue(user.getSurname());
        databaseRef.child("users").child(user.getUsername()).child("password").setValue(user.getPassword());
        databaseRef.child("users").child(user.getUsername()).child("phoneNo").setValue(user.getPhoneNumber());
        databaseRef.child("users").child(user.getUsername()).child("email").setValue(user.getEmail());

        databaseRef.child("users").child(user.getUsername()).setValue(user);
    }

    public void getUserSnapshot(String username, final OnGetDataListener listener) {
        listener.onStart();

        databaseRef.child("users").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listener.onSuccess(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getFacilitiesSnapshot(final OnGetDataListener listener) {
        listener.onStart();

        databaseRef.child("facilities").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listener.onSuccess(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getCompleteSnapshot(final OnGetDataListener listener) {
        listener.onStart();
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listener.onSuccess(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getFacilitySnapshot(String facilityName, final OnGetDataListener listener) {
        listener.onStart();

        firebaseManager.databaseRef.child("facilities").child(facilityName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listener.onSuccess(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setFacilities(String facilityName) {
        getFacilitySnapshot(facilityName, new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                Log.d("facName", facilityName);
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFailure() {

            }
        });
    }

    public void getSnapshotFromReference(DatabaseReference ref, final OnGetDataListener listener) {
        listener.onStart();

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listener.onSuccess(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void deleteFacility(String username, String facilityName, final OnGetDataListener listener) {
        listener.onStart();

        databaseRef.child("users").child(username).child("facilities").child(facilityName).removeValue();
        databaseRef.child("facilities").child(facilityName).removeValue();

        listener.onSuccess(null);
    }

    public void updateUserValue(User user, String node, String value) {
        Map<String, Object> update = new HashMap<>();
        update.put(node, value);
        firebaseManager.databaseRef.child("users").child(user.getUsername()).updateChildren(update);
    }

    public void updateUserSchedule(User user, int day, int hour, String node, boolean value) {
        Map<String, Object> update = new HashMap<>();
        update.put(node, value);
        firebaseManager.databaseRef.child("users").child(user.getUsername()).child("schedule").child(day+"").child(hour+"").updateChildren(update);
    }

    public void appointmentDatabaseUpdate(NormalUser normalUser, Facility facility, boolean appointmentMade, int day, int hour) {
        DatabaseReference userIntervalRef = firebaseManager.databaseRef.child("users").child(normalUser.getUsername()).child("schedule").child(day+"").child(hour+"");
        userIntervalRef.child("isAppointed").setValue(appointmentMade);
        userIntervalRef.child("isAvailable").setValue(!appointmentMade);
        userIntervalRef.child("appointedFacility").setValue(facility.getName());

        DatabaseReference facilityIntervalRef = firebaseManager.databaseRef.child("facilities").child(facility.getName()).child("schedule").child(day+"").child(hour+"");
        facilityIntervalRef.child("appointedUsers").child(normalUser.getUsername()).setValue("");
        //facilityIntervalRef.child("noOfAppointedUser").setValue(((FacilityTimeInterval)(facility.schedule.fullSchedule[day].fullDailySchedule[hour])).noOfAppointedUser);
    }

    public void updateFacilityQuota(Facility facility, TimeInterval interval, int quota) {
        Map<String, Object> update = new HashMap<>();
        update.put("quota", quota);
        firebaseManager.databaseRef.child("facilities").child(facility.getName()).child("schedule").child(interval.dailySchedule.day+"").child(interval.startingHour+"").updateChildren(update);
    }

}

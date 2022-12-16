package com.example.multifunctionalfitnessapp;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
}

package com.example.multifunctionalfitnessapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multifunctionalfitnessapp.Constants;
import com.example.multifunctionalfitnessapp.FirebaseManager;
import com.example.multifunctionalfitnessapp.NormalUser;
import com.example.multifunctionalfitnessapp.OnGetDataListener;
import com.example.multifunctionalfitnessapp.PersonTimeInterval;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.RecyclerViewInterface;
import com.example.multifunctionalfitnessapp.TimeInterval;
import com.example.multifunctionalfitnessapp.UserData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Fitness_Buddies_Activity extends AppCompatActivity implements RecyclerViewInterface {

    FirebaseManager firebaseManager = FirebaseManager.getInstance();

    RecyclerView recyclerView;
    FitnessBuddyContainerAdapter adapter;

    UserData userData;
    NormalUser normalUser;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fitness_buddies);

        userData = UserData.getInstance();
        normalUser = userData.normalUser;
        userData.fitnessBuddyTimeIntervals = setTimeIntervalsWithFitnessBuddies(normalUser);

        recyclerView = (RecyclerView) findViewById(R.id.fitnessBuddyContainer);

        adapter = new FitnessBuddyContainerAdapter(Fitness_Buddies_Activity.this, normalUser, Fitness_Buddies_Activity.this );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Fitness_Buddies_Activity.this));
    }

    public ArrayList<PersonTimeInterval> setTimeIntervalsWithFitnessBuddies(NormalUser normalUser) {
        ArrayList<PersonTimeInterval> tempIntervalArray = new ArrayList<>();

        for (int day = 0; day < 7; day++) {
            for (int hour = 0; hour < 24; hour++) {
                PersonTimeInterval interval = (PersonTimeInterval) normalUser.schedule.fullSchedule[day].fullDailySchedule[hour];

                if (interval.getFitnessBuddy() != null && interval.getFitnessBuddy().getUsername() != null && !interval.getFitnessBuddy().getUsername().equals("")) {
                    tempIntervalArray.add(interval);
                }
            }
        }

        /*PersonTimeInterval[] timeIntervals = new PersonTimeInterval[tempIntervalArray.size()];
        for (int i = 0; i < tempIntervalArray.size(); i++) {
            timeIntervals[i] = tempIntervalArray.get(i);
        }*/

        return tempIntervalArray;
    }

    @Override
    public void onItemClick(int position) {
    }

    @Override
    public void onItemLongClick(int position) {
        PersonTimeInterval interval = userData.fitnessBuddyTimeIntervals.get(position);

        DatabaseReference usersRef = firebaseManager.databaseRef.child("users");
        usersRef.child(normalUser.getUsername()).child("schedule").child(interval.getDailySchedule().day+"").child(interval.getStartingHour()+"").child("fitnessBuddy").setValue(null);
        usersRef.child(interval.getFitnessBuddy().getUsername()).child("schedule").child(interval.getDailySchedule().day+"").child(interval.getStartingHour()+"").child("fitnessBuddy").setValue(null);

        //interval.fitnessBuddy = null;
        interval.setFitnessBuddy(null);

        Toast.makeText(this, "Fitness Buddy removed", Toast.LENGTH_SHORT).show();

        userData.fitnessBuddyTimeIntervals.remove(position);
        adapter.notifyItemRemoved(position);
    }


}

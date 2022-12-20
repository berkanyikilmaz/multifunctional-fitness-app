package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.multifunctionalfitnessapp.AdapterClass;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.RecyclerViewInterface;
import com.example.multifunctionalfitnessapp.TimeInterval;

import java.util.ArrayList;

public class fitness_buddy_main extends AppCompatActivity implements RecyclerViewInterface {
    public ArrayList<TimeInterval> timeIntervals;
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fitness_buddies);
        recyclerView = (RecyclerView) findViewById(R.id.fitnessBuddyContainer);
        timeIntervals = new ArrayList<>();

        setTimeIntervals();
        setAdapter();
    }

    private void setAdapter() {
        AdapterClass adap = new AdapterClass(timeIntervals);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adap);
    }

    public void setTimeIntervals() {
    }

    @Override
    public void onItemClick(int position) {
        TimeInterval timeInterval = new TimeInterval(100);
    }

    @Override
    public void onItemLongClick(int position) {

    }
}
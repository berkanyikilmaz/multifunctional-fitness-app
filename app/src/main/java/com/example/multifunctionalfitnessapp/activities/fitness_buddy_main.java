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
        setContentView(R.layout.choose_time_interval_menu);
        recyclerView = (RecyclerView) findViewById(R.id.timeIntervalContainer);
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

    public void setTimeIntervals(){
        timeIntervals.add(new TimeInterval(1));
        timeIntervals.add(new TimeInterval(2));
        timeIntervals.add(new TimeInterval(3));
        timeIntervals.add(new TimeInterval(4));
        timeIntervals.add(new TimeInterval(1));
        timeIntervals.add(new TimeInterval(2));
        timeIntervals.add(new TimeInterval(3));
        timeIntervals.add(new TimeInterval(4));
        timeIntervals.add(new TimeInterval(1));
        timeIntervals.add(new TimeInterval(2));
        timeIntervals.add(new TimeInterval(3));
        timeIntervals.add(new TimeInterval(4));
        timeIntervals.add(new TimeInterval(1));
        timeIntervals.add(new TimeInterval(2));
        timeIntervals.add(new TimeInterval(3));
        timeIntervals.add(new TimeInterval(4));
        timeIntervals.add(new TimeInterval(1));
        timeIntervals.add(new TimeInterval(2));
        timeIntervals.add(new TimeInterval(3));
        timeIntervals.add(new TimeInterval(4));
        timeIntervals.add(new TimeInterval(1));
        timeIntervals.add(new TimeInterval(2));
        timeIntervals.add(new TimeInterval(3));
        timeIntervals.add(new TimeInterval(4));
        timeIntervals.add(new TimeInterval(1));
        timeIntervals.add(new TimeInterval(2));
        timeIntervals.add(new TimeInterval(3));
        timeIntervals.add(new TimeInterval(4));timeIntervals.add(new TimeInterval(1));
        timeIntervals.add(new TimeInterval(2));
        timeIntervals.add(new TimeInterval(3));
        timeIntervals.add(new TimeInterval(4));
        timeIntervals.add(new TimeInterval(1));
        timeIntervals.add(new TimeInterval(2));
        timeIntervals.add(new TimeInterval(3));
        timeIntervals.add(new TimeInterval(4));



    }

    @Override
    public void onItemClick(int position) {
        TimeInterval timeInterval = new TimeInterval(100);
    }

    @Override
    public void onItemLongClick(int position) {

    }
}
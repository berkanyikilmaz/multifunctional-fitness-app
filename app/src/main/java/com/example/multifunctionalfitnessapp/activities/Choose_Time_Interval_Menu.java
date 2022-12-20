package com.example.multifunctionalfitnessapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multifunctionalfitnessapp.NormalUser;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.RecyclerViewInterface;
import com.example.multifunctionalfitnessapp.TimeInterval;

public class Choose_Time_Interval_Menu extends AppCompatActivity implements RecyclerViewInterface {

    RecyclerView recyclerView;

    TimeInterval timeInterval0 = new TimeInterval(15);
    TimeInterval timeInterval1 =  new TimeInterval(16);
    TimeInterval timeInterval2 = new TimeInterval(17);
    TimeInterval timeInterval3 = new TimeInterval(18);
    TimeInterval timeInterval4 = new TimeInterval(19);
    TimeInterval exampleTimeIntervals[] = {timeInterval0, timeInterval1, timeInterval2, timeInterval3, timeInterval4};

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_time_interval_menu);

        recyclerView = (RecyclerView) findViewById(R.id.timeIntervalContainer);

        TimeIntervalContainerAdapter adapter = new TimeIntervalContainerAdapter(this, exampleTimeIntervals, this );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



    @Override
    public void onItemClick(int position) {
        //üstüne tıklayınca nolacak karar vermek lazım
    }

    @Override
    public void onItemLongClick(int position) {
        //üstüne uzun tıklayın nolacak
    }


}

package com.example.multifunctionalfitnessapp.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.multifunctionalfitnessapp.R;

public class Create_Schedule_Activity extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_schedule);
        //specifyUnavailablePeriods();



    }
    /*public void specifyUnavailablePeriods(){
        for(int n = 1; n< rowNumber; n++) {
            TableRow row = (TableRow)layout.getChildAt(n);
            TextView name = (TextView) row.getChildAt(2);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!name.getBackground().equals(Color.RED))
                        name.setBackgroundColor(Color.RED);
                    else
                        name.setBackgroundColor(Color.WHITE);
                }
            });



        }
    }*/
}
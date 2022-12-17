package com.example.multifunctionalfitnessapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multifunctionalfitnessapp.R;

public class Delete_Facility_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_facility_panel);

        yesButton();
        noButton();
    }

    private void yesButton() {
        Button yesButton = findViewById(R.id.yesButton);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete selected facility from current owners facility
                startActivity( new Intent( Delete_Facility_Activity.this, Facility_Owner_Main_Menu_Activity.class ) );

            }
        });
    }

    private void noButton() {
        Button noButton = findViewById(R.id.noButton);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( Delete_Facility_Activity.this, Facility_Owner_Main_Menu_Activity.class ) );
            }
        });
    }
}

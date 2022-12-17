package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.multifunctionalfitnessapp.R;

import java.util.ArrayList;

public class testActivity extends AppCompatActivity {
    String[] days = new String[]{"facility1", "facility2","facility3",};
    AutoCompleteTextView autoCompleteTextView2my;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        autoCompleteTextView2my = findViewById(R.id.autoCompleteTextView2);

        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item_test,days);

        autoCompleteTextView2my.setAdapter(adapterItems);

        autoCompleteTextView2my.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });

        //autoCompleteDropdown = new findViewById(R.id.autoCompleteTextView2);


        //val days = new getResources().getStringArray(R.array.days);
        //val m = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,days);
        //autoCompleteTextView.setAdapter(m);
    }
}
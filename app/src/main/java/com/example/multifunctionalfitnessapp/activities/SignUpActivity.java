package com.example.multifunctionalfitnessapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.databinding.SignUpScreenBinding;

public class SignUpActivity extends AppCompatActivity {

    String[] items = {"Normal User", "Facility Owner"};
    SignUpScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_screen);

        AutoCompleteTextView autoCompleteDropdown = findViewById(R.id.autoCompleteTextView2);
        String[] userType = getResources().getStringArray(R.array.userType);
        ArrayAdapter<String> adapterItems = new ArrayAdapter<String>(this, R.layout.dropdown_item, items);
        autoCompleteDropdown.setAdapter(adapterItems);

        autoCompleteDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        System.out.println("sign up on create view");
        return super.onCreateView(name, context, attrs);
    }
}
package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.databinding.SignUpScreenBinding;

public class SignUpActivity extends AppCompatActivity {

    SignUpScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_screen);
    }
}
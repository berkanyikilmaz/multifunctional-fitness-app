package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.multifunctionalfitnessapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_screen);

        registerSignUpTextButton();
        registerLoginButton();
    }

    private void registerSignUpTextButton() {
        TextView signUp = findViewById(R.id.signUpTextView);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( LogInActivity.this, SignUpActivity.class ) );
                Log.d("sigUpText", " is clicked!");
            }
        });
    }

    private void registerLoginButton() {
        TextInputEditText username = findViewById(R.id.userNameInput);
        TextInputEditText password = findViewById(R.id.passwordInput);

        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("loginButton", " is clicked!");
                // TODO try login
            }
        });
    }
}
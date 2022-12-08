package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.multifunctionalfitnessapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class LogInActivity extends AppCompatActivity {

    TextInputEditText username;
    TextInputEditText password;
    Button loginButton;

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
        username = findViewById(R.id.userNameInput);
        password = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();

                if (usernameText.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(LogInActivity.this, "Please enter all information", Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.d("loginButton", " is clicked!");
                // TODO try login
            }
        });
    }
}
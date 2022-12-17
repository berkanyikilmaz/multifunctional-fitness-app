package com.example.multifunctionalfitnessapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.multifunctionalfitnessapp.FirebaseManager;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.User;
import com.example.multifunctionalfitnessapp.UserData;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class LogInActivity extends AppCompatActivity {

    FirebaseManager firebaseManager = FirebaseManager.getInstance();

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

                handleLogin(usernameText, passwordText);
            }
        });
    }

    private void handleLogin(String usernameText, String passwordText) {
        String user;

        firebaseManager.databaseRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(usernameText)) {
                    String getPassword = snapshot.child(usernameText).child("password").getValue(String.class);

                    if (getPassword.equals(passwordText)) {
                        Toast.makeText(LogInActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
                        UserData userData = UserData.getInstance();
                        userData.login(usernameText);

                        switch(snapshot.child(usernameText).child("userType").getValue(String.class)) {
                            case "Normal User":
                                startActivity( new Intent( LogInActivity.this, User_Main_Menu_Activity.class ) );
                            case "Facility Owner":
                                startActivity( new Intent( LogInActivity.this, Facility_Owner_Main_Menu_Activity.class ) );
                        }
                    }
                    else {
                        Toast.makeText(LogInActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(LogInActivity.this, "Wrong username", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
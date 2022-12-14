package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.multifunctionalfitnessapp.NormalUser;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.User;

public class User_Profile_Activity extends AppCompatActivity {

    EditText name, surname, email, phoneNumber, password, userName;
    CheckBox nameBox,surnameBox,usernameBox,passwordBox, numberBox, mailBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_menu);
        User user = new NormalUser("", "", "", "", "", "");
        displayContents(user);
        // buttons and their onClick methods should be added here
        // buttons will function as ways to making changes to the attributes of the user
        // changing user's attributes will be similar to sign-up
    }

    public void displayContents(User user) {
        name = findViewById(R.id.editTextTextUsername);
        surname = findViewById(R.id.editTextTextPersonSurname);
        email = findViewById(R.id.editTextTextEmailAddress);
        phoneNumber = findViewById(R.id.editTextPhone);
        password = findViewById(R.id.editTextTextPassword);
        userName = findViewById(R.id.editTextTextPersonName);
        nameBox = findViewById(R.id.checkBox4);
        surnameBox = findViewById(R.id.checkBox2);
        mailBox = findViewById(R.id.checkBox3);
        usernameBox =findViewById(R.id.checkBox6);
        passwordBox = findViewById(R.id.checkBox7);
        numberBox = findViewById(R.id.checkBox5);
        nameBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///
            }
        });
        // we should set these according to the user
        name.setText(user.getName());
        surname.setText(user.getSurname());
        email.setText(user.getEmail());
        phoneNumber.setText(user.getPhoneNumber());
        password.setText(user.getPassword());
        userName.setText(user.getUsername());
    }

    public void updateContents() {
        // will update the contents after some of them are changes
        // will execute after a button is pressed
        // task of this method can be broken down to several methods
        // to update one by one, not all at once
    }
}
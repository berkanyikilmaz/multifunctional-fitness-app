package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.multifunctionalfitnessapp.FirebaseManager;
import com.example.multifunctionalfitnessapp.NormalUser;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.User;
import com.example.multifunctionalfitnessapp.UserData;

import java.util.HashMap;
import java.util.Map;

public class User_Profile_Activity extends AppCompatActivity {

    FirebaseManager firebaseManager = FirebaseManager.getInstance();

    EditText name, surname, email, phoneNumber, password, userName;
    CheckBox nameBox,surnameBox,usernameBox,passwordBox, numberBox, mailBox;

    User user;
    UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_menu);

        userData = UserData.getInstance();
        user = userData.getUser();

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
        nameBox = findViewById(R.id.nameCheckBox);
        surnameBox = findViewById(R.id.surnameCheckBox);
        mailBox = findViewById(R.id.emailCheckBox);
        usernameBox =findViewById(R.id.userNameCheckBox);
        passwordBox = findViewById(R.id.passwordCheckBox);
        numberBox = findViewById(R.id.phoneNumberCheckBox);
        name.setText(user.getName());
        surname.setText(user.getSurname());
        email.setText(user.getEmail());
        phoneNumber.setText(user.getPhoneNumber());
        password.setText(user.getPassword());
        userName.setText(user.getUsername());



        numberBox.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              onCheckboxClicked(view);
          }
        });
        nameBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCheckboxClicked(view);
            }
        });
        surnameBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCheckboxClicked(view);
            }
        });
        mailBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCheckboxClicked(view);
            }
        });
        usernameBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCheckboxClicked(view);
            }
        });
        passwordBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCheckboxClicked(view);
            }
        });
        numberBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCheckboxClicked(view);
            }
        });
    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.nameCheckBox:
                if (checked) {
                    name.setText(name.getText().toString());
                    user.setName(name.getText().toString());
                    firebaseManager.updateUserValue(user, "name", user.getName());
                }
                else
                break;
            case R.id.surnameCheckBox:
                if (checked) {
                    surname.setText(surname.getText().toString());
                    user.setSurname(surname.getText().toString());
                    firebaseManager.updateUserValue(user, "surname", user.getSurname());
                }
                else
                break;
            case R.id.emailCheckBox:
                if (checked) {
                    email.setText(email.getText().toString());
                    user.setEmail(email.getText().toString());
                    firebaseManager.updateUserValue(user, "email", user.getEmail());
                }
                else
                break;
            case R.id.phoneNumberCheckBox:
                if (checked) {
                    phoneNumber.setText(phoneNumber.getText().toString());
                    user.setPhoneNumber(phoneNumber.getText().toString());
                    firebaseManager.updateUserValue(user, "phoneNumber", user.getPhoneNumber());
                }
                else
                break;
            case R.id.passwordCheckBox:
                if (checked) {
                    password.setText(password.getText().toString());
                    user.setPassword(password.getText().toString());
                    firebaseManager.updateUserValue(user, "password", user.getPassword());
                }
                else
                break;
            case R.id.userNameCheckBox:
                if (checked) {
                    userName.setText(userName.getText().toString());
                    user.setUsername(userName.getText().toString());
                    firebaseManager.updateUserValue(user, "username", user.getUsername());
                }
                else
                break;
        }
    }
}
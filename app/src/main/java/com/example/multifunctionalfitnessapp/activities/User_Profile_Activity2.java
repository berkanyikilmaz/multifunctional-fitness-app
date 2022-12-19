package com.example.multifunctionalfitnessapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multifunctionalfitnessapp.FirebaseManager;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.User;
import com.example.multifunctionalfitnessapp.UserData;

public class User_Profile_Activity2 extends AppCompatActivity {

    FirebaseManager firebaseManager = FirebaseManager.getInstance();

    EditText name, surname, email, phoneNumber, password, userName;
    Button saveButton, cancelButton;

    User user;
    UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile2_menu);

        userData = UserData.getInstance();
        user = userData.getUser();

        displayContents(user);
        registerSaveButton();
        registerCancelButton();

        // buttons and their onClick methods should be added here
        // buttons will function as ways to making changes to the attributes of the user
        // changing user's attributes will be similar to sign-up

    }

    public void displayContents(User user) {

        name = findViewById(R.id.editName);
        surname = findViewById(R.id.editSurname);
        userName = findViewById(R.id.editUsername);
        password = findViewById(R.id.editPassword);
        phoneNumber = findViewById(R.id.editPhoneNumber);
        email = findViewById(R.id.editEmail);

        name.setText(user.getName());
        surname.setText(user.getSurname());
        userName.setText(user.getUsername());
        password.setText(user.getPassword());
        phoneNumber.setText(user.getPhoneNumber());
        email.setText(user.getEmail());
    }

    public void registerSaveButton(){
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setName(String.valueOf(name.getText()));
                firebaseManager.updateUserValue(user, "name", user.getName());
                user.setSurname(String.valueOf(surname.getText()));
                firebaseManager.updateUserValue(user, "surname", user.getSurname());
                user.setUsername(String.valueOf(userName.getText()));
                firebaseManager.updateUserValue(user, "username", user.getUsername());
                user.setPassword(String.valueOf(password.getText()));
                firebaseManager.updateUserValue(user, "password", user.getPassword());
                user.setPhoneNumber(String.valueOf(phoneNumber.getText()));
                firebaseManager.updateUserValue(user, "phoneNumber", user.getPhoneNumber());
                user.setEmail(String.valueOf(email.getText()));
                firebaseManager.updateUserValue(user, "email", user.getEmail());

                if(user.getUserType().equals("Normal User")){
                    startActivity(new Intent(User_Profile_Activity2.this, User_Main_Menu_Activity.class));
                }
                else{
                    startActivity(new Intent(User_Profile_Activity2.this, Facility_Owner_Main_Menu_Activity.class));
                }
            }
        });
    }

    public void registerCancelButton(){
        cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.getUserType().equals("Normal User")){
                    startActivity(new Intent(User_Profile_Activity2.this, User_Main_Menu_Activity.class));
                }
                else{
                    startActivity(new Intent(User_Profile_Activity2.this, Facility_Owner_Main_Menu_Activity.class));
                }

            }
        });
    }


}
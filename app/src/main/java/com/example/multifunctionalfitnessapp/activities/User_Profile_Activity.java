package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

        // we should set these according to the user
        name.setText(user.getName());
        surname.setText(user.getSurname());
        email.setText(user.getEmail());
        phoneNumber.setText(user.getPhoneNumber());
        password.setText(user.getPassword());
        userName.setText(user.getUsername());
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox2:
                if (checked)
                surname.setText(surnameBox.getText());
            else
                // Remove the meat
                break;
            case R.id.checkBox3:
                if (checked)
                email.setText(mailBox.getText());
            else
                // I'm lactose intolerant
                break;
            case R.id.checkBox4:
                if (checked)
                    name.setText(nameBox.getText());
                else
                    // Remove the meat
                    break;
            case R.id.checkBox5:
                if (checked)
                    phoneNumber.setText(numberBox.getText());
                else
                    // Remove the meat
                    break;
            case R.id.checkBox6:
                if (checked)
                    userName.setText(usernameBox.getText());
                else
                    // Remove the meat
                    break;
            case R.id.checkBox7:
                if (checked)
                    password.setText(passwordBox.getText());
                else
                    // Remove the meat
                    break;
        }
    }

    public void updateContents() {
        // will update the contents after some of them are changes
        // will execute after a button is pressed
        // task of this method can be broken down to several methods
        // to update one by one, not all at once
    }
}
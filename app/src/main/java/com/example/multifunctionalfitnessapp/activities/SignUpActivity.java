package com.example.multifunctionalfitnessapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.multifunctionalfitnessapp.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    FirebaseManager firebaseManager = FirebaseManager.getInstance();

    String[] items = {"Normal User", "Facility Owner"};

    EditText name;
    EditText surname;
    EditText username;
    EditText password;
    EditText phoneNo;
    EditText email;
    AutoCompleteTextView autoCompleteDropdown;
    TextView returnLogin;
    Button signUpCont;

    String userType = "Normal User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_screen);

        registerUsertypeDropdown();
        registerLoginTextButton();
        registerContinueButton();
    }

    private void registerUsertypeDropdown() {
        autoCompleteDropdown = findViewById(R.id.autoCompleteTextView2);
        ArrayAdapter<String> adapterItems = new ArrayAdapter<String>(this, R.layout.dropdown_item, items);
        autoCompleteDropdown.setAdapter(adapterItems);

        autoCompleteDropdown.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                userType = adapterView.getItemAtPosition(i).toString();
            }
        });
    }

    private void registerLoginTextButton() {
        returnLogin = findViewById(R.id.returnLogInText);
        returnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( SignUpActivity.this, LogInActivity.class ) );
            }
        });
    }

    private void registerContinueButton() {
        name = findViewById(R.id.nameInputField);
        surname = findViewById(R.id.surnameInputField);
        username = findViewById(R.id.usernameInputField);
        password = findViewById(R.id.passwordInputField);
        phoneNo = findViewById(R.id.phoneNoInputField);
        email = findViewById(R.id.mailInputField);

        signUpCont = findViewById(R.id.signUpContButton);

        signUpCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameText = name.getText().toString();
                String surnameText = surname.getText().toString();
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();
                String phoneNoText = phoneNo.getText().toString();
                String emailText = email.getText().toString();

                if (nameText.isEmpty() || surnameText.isEmpty() || usernameText.isEmpty() || passwordText.isEmpty() || phoneNoText.isEmpty() || emailText.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please enter all informations", Toast.LENGTH_SHORT).show();
                    return;
                }

                switch (userType) {
                    case "Normal User":
                        NormalUser newNormalUser = new NormalUser(nameText, surnameText, usernameText, passwordText, phoneNoText, emailText);
                        handleSignUp(newNormalUser, userType);
                        Log.d("signup", "normal user");
                        break;
                    case "Facility Owner":
                        FacilityOwner newFacilityOwner = new FacilityOwner(nameText, surnameText, usernameText, passwordText, phoneNoText, emailText);
                        handleSignUp(newFacilityOwner, userType);
                        firebaseManager.sendUserDataToDatabase(newFacilityOwner, userType);
                        Log.d("signup", "facilityOwner");
                        break;
                    default:
                        Log.d("signup", "Couldn't signed up");
                }
            }
        });
    }

    private void handleSignUp(User user, String userType) {
        firebaseManager.databaseRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.hasChild(user.getUsername())) {
                    Toast.makeText(SignUpActivity.this, "Username is already registered!", Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseManager.sendUserDataToDatabase(user, userType);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
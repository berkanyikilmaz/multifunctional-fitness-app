package com.example.multifunctionalfitnessapp;

enum UserType {
    NormalUser,
    FacilityOwner
}

public abstract class User {

    String name;
    String surname;
    UserType userType;

    public User() {
        
    }
}
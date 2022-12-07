package com.example.multifunctionalfitnessapp;

public abstract class User {

    String name;
    String surname;
    String password;
    String phoneNumber;
    String email;

    public User(String name, String surname, String password, String phoneNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    void displayProfile() {}
}
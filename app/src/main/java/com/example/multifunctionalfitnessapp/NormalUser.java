package com.example.multifunctionalfitnessapp;

import java.util.ArrayList;

public class NormalUser extends User {

    Schedule schedule;
    boolean wantsFitnessBuddy;

    public NormalUser(String name, String surname, String username, String password, String phoneNumber, String email) {
        super(name, surname, username, password, phoneNumber, email);
        wantsFitnessBuddy = false;
    }

    public ArrayList<NormalUser> findFitnessBuddy() {
        return null;
    }
}

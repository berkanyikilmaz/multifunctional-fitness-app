package com.example.multifunctionalfitnessapp;

import java.util.ArrayList;

public class NormalUser extends User {

    Schedule schedule;
    boolean wantsFitnessBuddy;

    public NormalUser(String name, String surname, String password, String phoneNumber, String email, Schedule schedule) {
        super(name, surname, surname, phoneNumber, email);
        this.schedule = schedule;
        wantsFitnessBuddy = false;
    }

    public ArrayList<NormalUser> findFitnessBuddy() {
        return null;
    }
}

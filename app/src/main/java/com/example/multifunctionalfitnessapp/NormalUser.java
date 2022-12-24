package com.example.multifunctionalfitnessapp;

import java.util.ArrayList;

public class NormalUser extends User {

    public Schedule schedule;

    public NormalUser() {
        super();
        schedule = Schedule.createEmptyUserSchedule();
        userType = "Normal User";
    }

    public NormalUser(String name, String surname, String username, String password, String phoneNumber, String email) {
        super(name, surname, username, password, phoneNumber, email);
        userType = "Normal User";
    }

    public Schedule getSchedule() {
        return schedule;
    }

}

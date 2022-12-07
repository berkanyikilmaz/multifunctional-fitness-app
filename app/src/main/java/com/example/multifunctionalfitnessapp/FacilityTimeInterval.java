package com.example.multifunctionalfitnessapp;

import java.util.ArrayList;

public class FacilityTimeInterval extends TimeInterval {

    // these are for facility appointments
    int quota;
    int noOfAppointedUser;
    ArrayList<NormalUser> AppointedUsers;

    boolean isFull() { return false; }

    boolean decreaseQuota() { return false; }

    boolean isMatching(NormalUser user) { return false; }

    @Override
    boolean addAppointment(NormalUser user) {
        return false;
    }

    @Override
    boolean removeAppointment(NormalUser user) {
        return false; 
    }
}

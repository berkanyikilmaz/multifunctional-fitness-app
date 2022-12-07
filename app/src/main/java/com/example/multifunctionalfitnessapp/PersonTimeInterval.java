package com.example.multifunctionalfitnessapp;

public class PersonTimeInterval extends TimeInterval {

    boolean isAvailable; // if the person is unavailable
    boolean isAppointed;
    Facility AppointedFacility;
    NormalUser fitnessBuddy;

    public PersonTimeInterval() {

    }

    boolean addFitnessBuddy(NormalUser user) {return false;}

    boolean removeFitnessBuddy(NormalUser user) { return false; }

    @Override
    boolean addAppointment(NormalUser user) {
        return false;
    }

    @Override
    boolean removeAppointment(NormalUser user) {
        return false;
    }
}

package com.example.multifunctionalfitnessapp;

public class PersonTimeInterval extends TimeInterval {

    boolean isAvailable; // if the person is unavailable
    public boolean isAppointed;
    Facility AppointedFacility;
    NormalUser fitnessBuddy;

    public PersonTimeInterval(int startingHour) {
        super(startingHour);
    }

    boolean addFitnessBuddy(NormalUser user) {return false;}

    boolean removeFitnessBuddy(NormalUser user) { return false; }

    @Override
    public boolean addAppointment(NormalUser user) {
        // if we add an appointment,
        // isAppointed should be changed to true, and AppointedFacility should be set
        // we will take this appointedFacility when the user chooses the facility.
        return false;
    }

    @Override
    boolean removeAppointment(NormalUser user) {
        return false;
    }

    public Facility getAppointedFacility() {
        return AppointedFacility;
    }
 }

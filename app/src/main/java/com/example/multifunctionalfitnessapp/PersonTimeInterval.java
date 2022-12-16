package com.example.multifunctionalfitnessapp;

public class PersonTimeInterval extends TimeInterval {

    public boolean isAvailable; // if the person is unavailable
    public boolean isAppointed;
    Facility AppointedFacility;
    NormalUser fitnessBuddy;

    public PersonTimeInterval() {
        super();
    }

    public PersonTimeInterval(int startingHour) {
        super(startingHour);
        isAvailable = true;
        isAppointed = false;
        AppointedFacility = null;
        fitnessBuddy = null;
    }

    boolean addFitnessBuddy(NormalUser user) {
        if(user.wantsFitnessBuddy){
            fitnessBuddy = user;
            return true;
        }
        else{
            return false;
        }
    }

    boolean removeFitnessBuddy(NormalUser user) {
        if(!user.wantsFitnessBuddy){
            fitnessBuddy = null;
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public boolean addAppointment(NormalUser user) {
        // if we add an appointment,
        // isAppointed should be changed to true, and AppointedFacility should be set
        // we will take this appointedFacility when the user chooses the facility.
        return false;
    }

    @Override
    boolean removeAppointment(NormalUser user) {
        this.isAppointed = false;
        return false;
    }

    public Facility getAppointedFacility() {
        return AppointedFacility;
    }
 }

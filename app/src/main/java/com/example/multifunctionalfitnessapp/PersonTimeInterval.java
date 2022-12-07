package com.example.multifunctionalfitnessapp;

public class PersonTimeInterval extends TimeInterval {

    boolean isAvailable; // if the person is unavailable
    boolean isAppointed;
    Facility AppointedFacility;
    NormalUser fitnessBuddy;

    public PersonTimeInterval(boolean isAvailable, boolean isAppointed,Facility f1, NormalUser u1) {
        this.isAvailable = isAvailable;
        this.isAppointed = isAppointed;
        this.AppointedFacility = f1;
        this.fitnessBuddy = u1;

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
    boolean addAppointment(NormalUser user) {
        this.isAppointed = true;
        return false;
    }

    @Override
    boolean removeAppointment(NormalUser user) {
        this.isAppointed = false;
        return false;
    }
}

package com.example.multifunctionalfitnessapp;

public class PersonTimeInterval extends TimeInterval {

    public boolean isAvailable; // if the person is unavailable
    public boolean isAppointed;
    public Facility appointedFacility;
    NormalUser fitnessBuddy;

    public PersonTimeInterval() {
        super();
    }

    public PersonTimeInterval(int startingHour) {
        super(startingHour);
        isAvailable = true;
        isAppointed = false;
        appointedFacility = null;
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
        //AppointedFacility = new Facility(new FacilityOwner("Ege", "Fitness", "egefitness", "***","05052","ege@gmail.com"));
        isAppointed = true;
        isAvailable = false;
        return true;
    }
    public
    @Override
    boolean removeAppointment(NormalUser user) {
        this.isAppointed = false;
        isAvailable = true;
        return false;
    }

    public Facility getAppointedFacility() {
        return appointedFacility;
    }
 }

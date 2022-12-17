package com.example.multifunctionalfitnessapp;

import com.example.multifunctionalfitnessapp.activities.Facility_Owner_Main_Menu_Activity;
import com.example.multifunctionalfitnessapp.activities.Make_Appointment_Activity;

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
        //AppointedFacility = new Facility(new FacilityOwner("Ege", "Fitness", "egefitness", "***","05052","ege@gmail.com"));
        isAppointed = true;
        return true;
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

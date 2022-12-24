package com.example.multifunctionalfitnessapp;

public class PersonTimeInterval extends TimeInterval {

    private boolean isAvailable; // if the person is unavailable
    private boolean isAppointed;
    private Facility appointedFacility;
    private NormalUser fitnessBuddy;
    private boolean wantsFitnessBuddy;

    public PersonTimeInterval() {
        super();
        isAvailable = true;
        isAppointed = false;
        appointedFacility = null;
        fitnessBuddy = null;
        wantsFitnessBuddy = false;
    }

    public boolean isAppointed() {
        return isAppointed;
    }

    public void setAppointed(boolean appointed) {
        isAppointed = appointed;
    }

    public void setAppointedFacility(Facility appointedFacility) {
        this.appointedFacility = appointedFacility;
    }

    public NormalUser getFitnessBuddy() {
        return fitnessBuddy;
    }

    public void setFitnessBuddy(NormalUser fitnessBuddy) {
        this.fitnessBuddy = fitnessBuddy;
    }

    public boolean isWantsFitnessBuddy() {
        return wantsFitnessBuddy;
    }

    public void setWantsFitnessBuddy(boolean wantsFitnessBuddy) {
        this.wantsFitnessBuddy = wantsFitnessBuddy;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public PersonTimeInterval(int startingHour) {
        super(startingHour);
        isAvailable = true;
        isAppointed = false;
        appointedFacility = null;
        fitnessBuddy = null;
        wantsFitnessBuddy = false;
    }

    public boolean addAppointment(Facility facility) {
        isAppointed = true;
        isAvailable = false;
        appointedFacility = facility;
        return true;
    }

    public boolean removeAppointment() {
        isAppointed = false;
        isAvailable = true;
        appointedFacility = null;
        return false;
    }

    public Facility getAppointedFacility() {
        return appointedFacility;
    }
 }

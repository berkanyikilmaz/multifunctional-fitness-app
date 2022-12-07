package com.example.multifunctionalfitnessapp;

public class Facility {

    String name;
    Schedule schedule;
    FacilityOwner owner;

    public Facility(FacilityOwner owner) {
        this.owner = owner;
        owner.addFacility(this);
    }

    boolean removeAppointment(NormalUser user, TimeInterval interval) { return false; }
}

package com.example.multifunctionalfitnessapp;

import java.util.ArrayList;

public class FacilityOwner extends User {

    ArrayList<Facility> facilities;

    public FacilityOwner() {
        super();
        facilities = new ArrayList<Facility>();
        userType = "Facility Owner";
    }
    
    public FacilityOwner(String name, String surname, String username, String password, String phoneNumber, String email) {
        super( name, surname, username, password, phoneNumber, email );
        facilities = new ArrayList<Facility>();
        userType = "Facility Owner";
    }

    boolean deleteFacility(int index) {
        if (this.facilities.remove(index) != null) return true;
        return false;
    }

    public boolean addFacility(Facility facility) {
        return this.facilities.add(facility);
    }

    public ArrayList<Facility> getFacilities() {
        return facilities;
    }
}

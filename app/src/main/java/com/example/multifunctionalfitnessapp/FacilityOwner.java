package com.example.multifunctionalfitnessapp;

import java.util.ArrayList;

public class FacilityOwner extends User {

    ArrayList<Facility> facilities;
    
    public FacilityOwner() {
        super();
        facilities = new ArrayList<>();
    }

    boolean deleteFacility(int index) { return false; }

    boolean addFacility(Facility facility) { return false;}

}

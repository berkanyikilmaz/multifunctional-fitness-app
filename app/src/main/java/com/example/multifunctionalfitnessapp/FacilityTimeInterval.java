package com.example.multifunctionalfitnessapp;

import java.util.ArrayList;

public class FacilityTimeInterval extends TimeInterval {

    // these are for facility appointments
    private int quota;
    private int noOfAppointedUser;
    private ArrayList<NormalUser> appointedUsers;
    private boolean isSelected = false; // for facility schedule creation

    public FacilityTimeInterval() { super(); }

    public FacilityTimeInterval(int startingHour) {
        super(startingHour);
        quota = 0;
        appointedUsers = new ArrayList<NormalUser>();
    }

    public int getQuota() {
        return quota;
    }

    public boolean setQuota(int quota) {
        if ( quota >= getNoOfAppointedUser() ){
            this.quota = quota;
            return true;
        }
        return false;
    }

    public int getNoOfAppointedUser() {
        return noOfAppointedUser;
    }

    public void setNoOfAppointedUser(int noOfAppointedUser) {
        this.noOfAppointedUser = noOfAppointedUser;
    }

    public ArrayList<NormalUser> getAppointedUsers() {
        return appointedUsers;
    }

    public void setAppointedUsers(ArrayList<NormalUser> appointedUsers) {
        this.appointedUsers = appointedUsers;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isFull() {
        return quota == getNoOfAppointedUser();
    }

    /*ArrayList<NormalUser> isMatching(NormalUser user) {
        ArrayList<NormalUser> fitnessBuddies = new ArrayList();
        for ( NormalUser matchingUser : appointedUsers ){
            if (user.wantsFitnessBuddy) fitnessBuddies.add(matchingUser);
        }
        return  fitnessBuddies;
    }*/

    public boolean addAppointment(NormalUser user) {
        appointedUsers.add(user);
        return false;
    }

    public boolean removeAppointment(NormalUser user) {
        return false;
    }
}

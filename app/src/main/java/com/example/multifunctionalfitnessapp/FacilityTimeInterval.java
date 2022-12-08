package com.example.multifunctionalfitnessapp;

import java.util.ArrayList;

public class FacilityTimeInterval extends TimeInterval {

    // these are for facility appointments
    int quota;
    int noOfAppointedUser;
    ArrayList<NormalUser> appointedUsers;

    public int getQuota() {
        return quota;
    }

    public int getNoOfAppointedUser() {
        return noOfAppointedUser;
    }

    public ArrayList<NormalUser> getAppointedUsers() {
        return appointedUsers;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public void setNoOfAppointedUser(int noOfAppointedUser) {
        this.noOfAppointedUser = noOfAppointedUser;
    }

    public void setAppointedUsers(ArrayList<NormalUser> appointedUsers) {
        this.appointedUsers = appointedUsers;
    }

    boolean isFull() {
        return quota == noOfAppointedUser;
    }

    boolean decreaseQuota(int newQuota) {
        if ( newQuota >= noOfAppointedUser ){
            this.quota = newQuota;
            return true;
        }
        return false;
    }

    ArrayList<NormalUser> isMatching(NormalUser user) {
        ArrayList<NormalUser> fitnessBuddies = new ArrayList();
        for ( NormalUser matchingUser : appointedUsers ){
            if (user.wantsFitnessBuddy) fitnessBuddies.add(matchingUser);
        }
        return  fitnessBuddies;
    }

    @Override
    boolean addAppointment(NormalUser user) {
        super.addAppointment(user);
    }

    @Override
    boolean removeAppointment(NormalUser user) {
        super.removeAppointment(user);
    }
}

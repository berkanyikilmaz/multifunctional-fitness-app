package com.example.multifunctionalfitnessapp;

import java.util.ArrayList;

public class FacilityTimeInterval extends TimeInterval {

    // these are for facility appointments
    int quota;
    int noOfAppointedUser;
    ArrayList<NormalUser> AppointedUsers;

    public FacilityTimeInterval(int quota, int noOfAppointedUser, ArrayList<NormalUser> appointedUsers) {
        this.quota = quota;
        this.noOfAppointedUser = noOfAppointedUser;
        AppointedUsers = appointedUsers;
    }

    public FacilityTimeInterval(int noOfAppointedUser) {
        this.noOfAppointedUser = noOfAppointedUser;
    }

    public FacilityTimeInterval(){

    }

    boolean isFull() {
        if (this.quota == this.noOfAppointedUser){
            return true;
        }
        else{
            return false;
        }

    }

    boolean decreaseQuota() {


        if(noOfAppointedUser > this.quota -1 ) {
            return false;

        }
        else {
            //means if((noOfAppointedUser <= this.quota -1)
            this.quota--;
            return false;
        }

    }

    ArrayList<NormalUser> isMatching(NormalUser user) {
        // we know that user is appointed for the same interval
        ArrayList<NormalUser> fitnessBudyWants = new ArrayList<NormalUser>();

        for (NormalUser otherUser : AppointedUsers) {
            if (!otherUser.equals(user) && otherUser.wantsFitnessBuddy) {
                fitnessBudyWants.add(otherUser);
            }
        }return fitnessBudyWants;
    }

    @Override
    boolean addAppointment(NormalUser user) {
        if(noOfAppointedUser < quota){
            AppointedUsers.add(user);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    boolean removeAppointment(NormalUser user) {
        noOfAppointedUser--;
        AppointedUsers.remove(user);
        return true;
    }
}

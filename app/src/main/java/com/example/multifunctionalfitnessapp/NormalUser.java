package com.example.multifunctionalfitnessapp;

import java.util.ArrayList;

public class NormalUser extends User {

    public Schedule schedule;
    public ArrayList<PersonTimeInterval> appointedHours = new ArrayList<>();

    public NormalUser() {
        super();
        schedule = Schedule.createEmptyUserSchedule();
        userType = "Normal User";
    }

    public NormalUser(String name, String surname, String username, String password, String phoneNumber, String email) {
        super(name, surname, username, password, phoneNumber, email);
        userType = "Normal User";
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void addAppointedHours() {

        for (int j = 0; j < 24; j++) {
            for (int k = 0; k < 7; k++) {
                TimeInterval interval = schedule.fullSchedule[k].fullDailySchedule[j];
                if (((PersonTimeInterval) interval).isAppointed)
                    appointedHours.add((PersonTimeInterval) interval);
            }
        }
}
    public ArrayList<NormalUser> findFitnessBuddy(TimeInterval selectedInterval,Facility f1) {
        ArrayList<NormalUser> appointedUsersInInterval = new ArrayList<>();
       for(int k = 0; k<appointedHours.size();k++){
           if(appointedHours.get(k).equals(selectedInterval)){
              appointedUsersInInterval= ((FacilityTimeInterval)f1.schedule.getTimeInterval(selectedInterval)).appointedUsers;
           }


        }
        return appointedUsersInInterval;
    }
}

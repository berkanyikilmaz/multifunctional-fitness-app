package com.example.multifunctionalfitnessapp;

public class TimeInterval {
    
    int startingHour;
    final int INTERVAL = 60; // in terms of minutes
    DailySchedule dailySchedule;

    public TimeInterval() {}

    public TimeInterval(int startingHour) {
        this.startingHour = startingHour;
    }

    boolean addAppointment(NormalUser user) {

        return false;
    }
    public int getINTERVAL(){
        return this.INTERVAL;
    }

    public int getStartingHour(){
        return startingHour;
    }

    boolean removeAppointment(NormalUser user) { return false; }

}

package com.example.multifunctionalfitnessapp;

public class TimeInterval {
    
    int startingHour;
    final int INTERVAL = 60; // in terms of minutes
    DailySchedule dailySchedule;

    public TimeInterval(int startingHour) {
        this.startingHour = startingHour;
    }

    boolean addAppointment(NormalUser user) { return false; }

    boolean removeAppointment(NormalUser user) { return false; }

}

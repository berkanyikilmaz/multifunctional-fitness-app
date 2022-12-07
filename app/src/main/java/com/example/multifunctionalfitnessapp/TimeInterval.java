package com.example.multifunctionalfitnessapp;

public class TimeInterval {
    
    int startingHour;
    final int INTERVAL = 60; // in terms of minutes
    DailySchedule dailySchedule;

    public TimeInterval(int startingHour, DailySchedule dailySchedule) {
        this.startingHour = startingHour;
        this.dailySchedule = dailySchedule;
    }

    public TimeInterval() {

    }

    boolean addAppointment(NormalUser user) {

        return false;
    }

    boolean removeAppointment(NormalUser user) { return false; }

}

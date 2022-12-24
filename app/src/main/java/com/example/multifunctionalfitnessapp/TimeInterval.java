package com.example.multifunctionalfitnessapp;

public class TimeInterval {
    
    private int startingHour;
    final int INTERVAL = 60; // in terms of minutes
    private DailySchedule dailySchedule;

    public TimeInterval() {}

    public TimeInterval(int startingHour) {
        this.startingHour = startingHour;
    }

    public int getStartingHour(){
        return startingHour;
    }

    public void setStartingHour(int startingHour) {
        this.startingHour = startingHour;
    }

    public DailySchedule getDailySchedule() {
        return dailySchedule;
    }

    public void setDailySchedule(DailySchedule dailySchedule) {
        this.dailySchedule = dailySchedule;
    }
}

package com.example.multifunctionalfitnessapp;
//test

public class DailySchedule extends Schedule {

    // these are the date of
    int day;
    int month;
    TimeInterval[] fullDailySchedule;

    public DailySchedule() {
     fullDailySchedule = new TimeInterval[24];
    }

    @Override
    TimeInterval getTimeInterval(TimeInterval interval) {
        return super.getTimeInterval(interval);
    }
}

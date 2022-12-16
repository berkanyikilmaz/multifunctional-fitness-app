package com.example.multifunctionalfitnessapp;
//test

import java.sql.Time;

public class DailySchedule extends Schedule {

    // these are the date of
    int day;
    int month;
    TimeInterval[] fullDailySchedule;

    public DailySchedule() {
     fullDailySchedule = new TimeInterval[24];
    }

    public TimeInterval getInterval(int startingHour) {
        if (startingHour >= 0 && startingHour < 24) {
            return fullDailySchedule[startingHour];
        }
        return null;
    }

    @Override
    TimeInterval getTimeInterval(TimeInterval interval) {
        return super.getTimeInterval(interval);
    }

}

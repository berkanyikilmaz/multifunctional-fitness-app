package com.example.multifunctionalfitnessapp;
//test

import java.sql.Time;
import java.time.DayOfWeek;

public class DailySchedule extends Schedule {

    // these are the date of
    public int day;
    public TimeInterval[] fullDailySchedule;

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

    public static DailySchedule createNormalUserDailySchedule(int day) {
        DailySchedule newSchedule = new DailySchedule();
        newSchedule.day = day;

        for (int i = 0; i < newSchedule.fullDailySchedule.length; i++) {
            newSchedule.fullDailySchedule[i] = new PersonTimeInterval(i);
        }

        return newSchedule;
    }

    public static DailySchedule createFacilityDailySchedule(int day) {
        DailySchedule newSchedule = new DailySchedule();
        newSchedule.day = day;

        for (int i = 0; i < newSchedule.fullDailySchedule.length; i++) {
            newSchedule.fullDailySchedule[i] = new FacilityTimeInterval(i);
        }

        return newSchedule;
    }
}

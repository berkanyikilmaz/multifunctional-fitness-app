package com.example.multifunctionalfitnessapp;

import java.util.ArrayList;

enum DisplayType {
    DAILY,
    WEEKLY
}

public class Schedule {
    
    DailySchedule[] fullSchedule;
    DisplayType displayType = DisplayType.WEEKLY;

    public Schedule() {

    }

    TimeInterval getTimeInterval(TimeInterval interval) { return null; }

    void removeAppointment(TimeInterval period) {}

    ArrayList<TimeInterval> findAvailablePeriods(Schedule facilitySchedule, Schedule userSchedule) {return null;}
}

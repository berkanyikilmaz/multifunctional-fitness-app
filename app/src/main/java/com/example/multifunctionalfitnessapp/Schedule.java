package com.example.multifunctionalfitnessapp;

import java.util.ArrayList;

enum DisplayType {
    DAILY,
    WEEKLY
}

public class Schedule {
    
    public DailySchedule[] fullSchedule;
    DisplayType displayType = DisplayType.WEEKLY;

    public Schedule() {
     fullSchedule = new DailySchedule[7];
    }

    TimeInterval getTimeInterval(TimeInterval interval) {
        int startingHour = interval.startingHour;
        int day = interval.dailySchedule.day;
        int month = interval.dailySchedule.month;
        return fullSchedule[day].fullDailySchedule[startingHour];
    }

    public void removeAppointment(TimeInterval period) {
    int removingHour = period.startingHour;
    fullSchedule[period.dailySchedule.day].fullDailySchedule[removingHour] = null;
    }

    ArrayList<Schedule> findAvailablePeriods(Schedule facilitySchedule, Schedule userSchedule) {
        ArrayList<Schedule> availablePeriods = new ArrayList<>();
        for(int day = 0; day<7;day++){
            for(int hours =0; hours<24;hours++){
                TimeInterval available;

                if( ((FacilityTimeInterval)facilitySchedule.fullSchedule[day].fullDailySchedule[hours]).isFull() ){

                }
                  else  if(!((PersonTimeInterval)(userSchedule.fullSchedule[day].fullDailySchedule[hours])).isAppointed){

                }
                  else{
                      Schedule eachDay = new Schedule();
                      eachDay.fullSchedule[day].fullDailySchedule[hours] = userSchedule.fullSchedule[day].fullDailySchedule[hours];
                      availablePeriods.add(eachDay);
                }

                    }

        }

        return availablePeriods;
    }

    public static Schedule createEmptyUserSchedule() {
        Schedule newSchedule = new Schedule();

        for (int i = 0; i < 7; i++) {
            newSchedule.fullSchedule[i] = DailySchedule.createNormalUserDailySchedule(i);
        }

        return newSchedule;
    }

    public static Schedule createEmptyFacilitySchedule() {
        Schedule newSchedule = new Schedule();

        for (int i = 0; i < 7; i++) {
            newSchedule.fullSchedule[i] = DailySchedule.createFacilityDailySchedule(i);
        }

        return newSchedule;
    }
}

package com.example.multifunctionalfitnessapp;

public class Facility {

    String name;
    Schedule schedule;
    FacilityOwner owner;

    public Facility(FacilityOwner owner) {
        this.owner = owner;
        owner.addFacility(this);
    }

    boolean removeAppointment(NormalUser user, TimeInterval interval) {
        this.schedule.getTimeInterval(interval).removeAppointment(user);
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(FacilityOwner owner) {
        this.owner = owner;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public FacilityOwner getOwner() {
        return owner;
    }

    public Schedule getSchedule() {
        return schedule;
    }
}

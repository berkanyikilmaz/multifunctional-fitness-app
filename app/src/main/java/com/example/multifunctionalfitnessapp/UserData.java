package com.example.multifunctionalfitnessapp;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class UserData {

    FirebaseManager firebaseManager = FirebaseManager.getInstance();

    public static UserData userData = null;

    public NormalUser normalUser;
    public FacilityOwner facilityOwner;

    public String username;
    public String name;

    public String existingUserType;

    public Facility facility;

    public UserData() {
        username = null;
        name = null;
        normalUser = new NormalUser();
        facilityOwner = new FacilityOwner();
        facility = new Facility();
    }

    public static UserData getInstance() {
        if (userData == null) {
            userData = new UserData();
        }
        return userData;
    }

    public void login(String username) {
        this.username = username;
    }

    public void setNormalUserName(String name) {
        this.name = name;
    }

    public void setNormalUserFromSnapshot(DataSnapshot snapshot) {
        DataSnapshot userSnapshot = snapshot.child("users").child(userData.username);

        String name = userSnapshot.child("name").getValue(String.class);
        String surname = userSnapshot.child("surname").getValue(String.class);
        String username = userSnapshot.child("username").getValue(String.class);
        String password = userSnapshot.child("password").getValue(String.class);
        String phoneNo = userSnapshot.child("phoneNumber").getValue(String.class);
        String email = userSnapshot.child("email").getValue(String.class);

        userData.normalUser.setName(name);
        userData.normalUser.setSurname(surname);
        userData.normalUser.setUsername(username);
        userData.normalUser.setPassword(password);
        userData.normalUser.setPhoneNumber(phoneNo);
        userData.normalUser.setEmail(email);

        Schedule userSchedule = Schedule.createEmptyUserSchedule();

        for (int day = 0; day < 7; day++) {
            for (int hour = 0; hour < 24; hour++) {
                DataSnapshot intervalSnapshot = userSnapshot.child("schedule").child(day+"").child(hour+"");

                PersonTimeInterval interval = new PersonTimeInterval();
                interval.isAvailable = intervalSnapshot.child("isAvailable").getValue(boolean.class);
                interval.isAppointed = intervalSnapshot.child("isAppointed").getValue(boolean.class);
                interval.startingHour = Integer.parseInt(intervalSnapshot.getKey());

                if (interval.isAppointed) { //JUST FOR NAME REFERENCE
                    DataSnapshot facilitySnapshot = snapshot.child("facilities").child(intervalSnapshot.child("appointedFacility").getValue(String.class));

                    Facility newFacility = new Facility();
                    newFacility.setName(facilitySnapshot.getKey());

                    interval.appointedFacility = newFacility;
                }

                NormalUser fitnessBuddy = new NormalUser();
                fitnessBuddy.setUsername(intervalSnapshot.child("fitnessBuddy").getValue(String.class));
                interval.fitnessBuddy = fitnessBuddy;

                interval.dailySchedule = userSchedule.fullSchedule[day];
                userSchedule.fullSchedule[day].fullDailySchedule[hour] = interval;
            }
        }

        normalUser.schedule = userSchedule;
        existingUserType = "Normal User";
    }

    //this method uses complete snapshot
    public void setFacilityOwnerFromDatabase(DataSnapshot snapshot) {
        DataSnapshot userSnapshot = snapshot.child("users").child(userData.username);

        String name = userSnapshot.child("name").getValue(String.class);
        String surname = userSnapshot.child("surname").getValue(String.class);
        String username = userSnapshot.child("username").getValue(String.class);
        String password = userSnapshot.child("password").getValue(String.class);
        String phoneNo = userSnapshot.child("phoneNumber").getValue(String.class);
        String email = userSnapshot.child("email").getValue(String.class);

        facilityOwner.setName(name);
        facilityOwner.setSurname(surname);
        facilityOwner.setUsername(username);
        facilityOwner.setPassword(password);
        facilityOwner.setPhoneNumber(phoneNo);
        facilityOwner.setEmail(email);

        facilityOwner.facilities = new ArrayList<Facility>();

        for(DataSnapshot childFacility : userSnapshot.child("facilities").getChildren()) {
            DataSnapshot facilitySnapshot = snapshot.child("facilities").child(childFacility.getKey());

            Facility newFacility = new Facility(facilityOwner);
            newFacility.setName(facilitySnapshot.getKey());

            setFacilitySchedule(newFacility, facilitySnapshot);
        }

        existingUserType = "Facility Owner";
    }

    public void setFacilityFromSnapshot(DataSnapshot snapshot) {
        facility = new Facility();
        facility.setName(snapshot.getKey());

        setFacilitySchedule(facility, snapshot);
    }

    public void setFacilitySchedule(Facility facility, DataSnapshot snapshot) {
        Schedule facilitySchedule = Schedule.createEmptyFacilitySchedule();

        for (int day = 0; day < 7; day++) {
            for (int hour = 0; hour < 24; hour++) {
                DataSnapshot intervalSnapshot = snapshot.child("schedule").child(day+"").child(hour+"");
                FacilityTimeInterval interval = new FacilityTimeInterval();

                interval.isSelected = intervalSnapshot.child("isSelected").getValue(boolean.class);
                interval.quota = intervalSnapshot.child("quota").getValue(int.class);
                interval.startingHour = Integer.parseInt(intervalSnapshot.getKey());

                interval.appointedUsers = new ArrayList<>();
                for (DataSnapshot appointedUserSnapshot : intervalSnapshot.child("appointedUsers").getChildren()) {
                    NormalUser newUser = new NormalUser();
                    newUser.setUsername(appointedUserSnapshot.getKey());

                    interval.appointedUsers.add(newUser);
                }

                interval.dailySchedule = facilitySchedule.fullSchedule[day];
                facilitySchedule.fullSchedule[day].fullDailySchedule[hour] = interval;
            }
        }

        facility.setSchedule(facilitySchedule);
    }

    public Facility returnFacilityFromSnapshot(DataSnapshot snapshot) {
        Facility newFacility = new Facility();
        newFacility.setName(snapshot.getKey());

        Schedule facilitySchedule = Schedule.createEmptyFacilitySchedule();

        for (int day = 0; day < 7; day++) {
            for (int hour = 0; hour < 24; hour++) {
                FacilityTimeInterval interval = snapshot.child("schedule").child(day+"").child(hour+"").getValue(FacilityTimeInterval.class);
                //interval.startingHour = Integer.parseInt(interval.getKey());

                interval.dailySchedule = facilitySchedule.fullSchedule[day];
                facilitySchedule.fullSchedule[day].fullDailySchedule[hour] = interval;
            }
        }

        newFacility.setSchedule(facilitySchedule);
        return newFacility;
    }

    public User getUser() {
        if (existingUserType.equals("Facility Owner")) return facilityOwner;
        else if (existingUserType.equals("Normal User")) return normalUser;
        else return null;
    }

    public NormalUser getNormalUser() {
        return normalUser;
    }
}

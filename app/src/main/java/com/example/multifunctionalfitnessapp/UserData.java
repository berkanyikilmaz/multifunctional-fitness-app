package com.example.multifunctionalfitnessapp;

public class UserData {

    public static UserData userData = null;

    public String username;

    public UserData() {
        username = null;
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
}

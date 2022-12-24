package com.example.multifunctionalfitnessapp;

public abstract class User {

    String name;
    String surname;
    String username;
    String password;
    String phoneNumber;
    String email;
    String userType;

    public User() {
        name = "";
        surname = "";
        username = "";
        password = "";
        phoneNumber = "";
        email = "";
        userType = "";
    }

    public User(String name, String surname, String username, String password, String phoneNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userType = null;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getUsername() { return username; }
    public String getPassword() {
        return password;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public String getUserType() { return userType; }

    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setUserType(String userType) { this.userType = userType; }

}
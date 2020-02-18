package com.shop.model;

/** User class - for login and shopping purposes. */

public class User {

//  User parameters:
    private String userName;
    private String password;
    private String email;
    private String phoneNo;

//  User class constructor:
    public User(String userName, String password, String email, String phoneNo) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
    }

//  User getters and setters:
    public String getUserName() {
        return userName;
    }
    public String setUserName(String userName) {
        this.userName = userName;
        return userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

}
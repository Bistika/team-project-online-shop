package com.shop.model;

/** User class - for login and shopping purposes. */

public class User implements Writable {

//  User parameters:
    private String email;
    private String password;
    private String userName;
    private String phoneNo;
    private String role;

//  User class constructor:
    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String email, String password, String userName, String phoneNo, String role) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.phoneNo = phoneNo;
        this.role = role;
    }

    @Override
    public String toString (){
        return "Name: " + userName + ", password: " + "private" + ", Email: " + email + ", PhoneNo: " + phoneNo + ", Role: " + role;
    }
    public String printCompleteUserData() {
        return "Name: " + userName + ", password: " + password + ", Email: " + email + ", PhoneNo: " + phoneNo;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toDb() {
        return  email + "|" + password + "|" + userName + "|" + phoneNo + "|" + role;

    }
}
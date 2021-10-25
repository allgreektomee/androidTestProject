package com.devcation.project.feed.list;

public class UserData {
    public String phoneNumber;
    public String name;
    public String userName;
    public String password;

    public UserData(String phoneNumber, String name, String userName, String password) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.userName = userName;
        this.password = password;
    }
    //컨트롤 n or 커맨드 n  Getter and Setter

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

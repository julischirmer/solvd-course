package com.solvd.homework2;

import com.solvd.homework2.enums.UserAccess;

public class User {

    private String username;
    private String password;
    private UserAccess userAccess;

    User(String username,String password, UserAccess userAccess){
        this.username = username;
        this.password = password;
        this.userAccess = userAccess;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

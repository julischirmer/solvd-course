package com.solvd.homework2.enums;

public enum UserAccess {
    ADMIN("Admin"),
    STUDENT("Student"),
    PROFESSOR("Professor");
    private final String access;

    UserAccess(String access){
        this.access = access;
    }

    public String getGrade() {
        return access;
    }

}

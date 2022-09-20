package com.solvd.homework2.enums;

public enum CourseStatus {
    OPEN("Open"),
    CLOSE("Close");
    private final String status;

    CourseStatus(String status){
        this.status = status;
    }

    public String getCourseStatus() {
        return status;
    }
}

package com.solvd.homework2.enums;
public enum CourseAvailability {
    AVAILABLE("Available"),
    FULL("Full");
    private final String availability;

    CourseAvailability(String availability) {
        this.availability = availability;
    }

    public String getCourseAvailability() {
        return availability;
    }
}
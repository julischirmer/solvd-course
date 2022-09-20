package com.solvd.homework2.enums;

public enum Grade {

    DISTINCTION("Distinction"),
    MERIT("Merit"),
    PASS("Pass"),
    FAIL("Fail");
    private final String grade;

    Grade(String grade){
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

}
package com.solvd.homework2.enums;

public enum DepartmentType {
    SCIENCE("Science"),
    ART("Art"),
    ECONOMIC("Economic"),
    ENGINEERING("Engineering"),
    LAW ("Law");
    final private String department;

    DepartmentType(String Department) {
        this.department = Department;
    }

    public String getDepartment(){
        return department;
    }

}

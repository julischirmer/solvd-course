package com.solvd.homework2;

import com.solvd.homework2.enums.DepartmentType;

public class Department {
    private int idDepartment;
    private DepartmentType typeDepartment;
    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public void setTypeDepartment(DepartmentType typeDepartment){
        this.typeDepartment = typeDepartment;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public DepartmentType getTypeDepartment() {
        return typeDepartment;
    }

    public Department(int idDep, DepartmentType typeDepartment){
       setIdDepartment(idDep);
       setTypeDepartment(typeDepartment);
    }

}

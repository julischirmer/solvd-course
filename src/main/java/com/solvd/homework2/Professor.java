package com.solvd.homework2;

import com.solvd.homework2.exceptions.InvalidSalary;
import com.solvd.homework2.functionalInterfaces.ISalaryBonus;

import java.time.LocalDate;
import java.time.Period;

public final class Professor extends Person {
    private double salary;

    public Professor(int dni, String name, String lastname, double salary) {
        this.setDni(dni);
        this.setName(name);
        this.setLastname(lastname);
        try {
            this.setSalary(salary);
        } catch (InvalidSalary e) {
            System.out.println(e.getMessage());
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) throws InvalidSalary {
        if (salary > 0) {
            this.salary = salary;
        } else {
            throw new InvalidSalary("The salary must be upper than zero");
        }

    }

    public void increaseSalary(double bonus, ISalaryBonus<Double> increase){
        increase.increaseSalary(bonus);
    }


    @Override
    public int getAge() {
        int age = Period.between(this.getBirthday(), LocalDate.now()).getYears();
        return age;
    }

    @Override
    public String toString() {
        return "\nProfessor{" +
                "dni=" + this.getDni() +
                ", name=" + this.getName() +
                ", last name=" + this.getLastname() +
                ", salary=" + this.getSalary() +
                '}';
    }
}

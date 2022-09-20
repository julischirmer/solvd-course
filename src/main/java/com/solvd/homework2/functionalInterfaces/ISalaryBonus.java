package com.solvd.homework2.functionalInterfaces;
@FunctionalInterface
public interface ISalaryBonus<T> {
    void increaseSalary(T amount);
}
package com.solvd.homework2;

import com.solvd.homework2.exceptions.InvalidMailException;
import com.solvd.homework2.interfaces.ICountryResidence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.Period;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class Student extends Person implements ICountryResidence {

    private final Logger logger = LogManager.getLogger(Student.class.getName());
    private double averagemark;
    private String countryResidence;
    public double getAverageMark() {
        return averagemark;
    }

    public void setAverageMark(double averageMark) {
        this.averagemark = averageMark;
    }

    public Student(int dni, String name, String lastName , double averagemark){
        super.setDni(dni);
        super.setName(name);
        super.setLastname(lastName);
        setAverageMark(averagemark);
    }
    public Student(int dni, String name, String lastname, String mail, LocalDate birthday) throws InvalidMailException {
        this.setDni(dni);
        this.setName(name);
        this.setLastname(lastname);
        this.setMail(mail);
        this.setBirthday(birthday);
    }

    public Student(int dni){
        super();
        this.setDni(dni);
    }

    public void findStudent(LinkedList<Student> students) {
        Scanner scanner = new Scanner(System.in);
        try {
            logger.info("Insert dni to find student: ");
            Student studentcompare = new Student(scanner.nextInt());

            if (students.contains(studentcompare)) {
                logger.info("The student was found ");
                Student studentFind = students.get(students.indexOf(studentcompare));
                logger.info(studentFind);

            } else {
                logger.warn("The student wasn't found");
            }
        } catch (InputMismatchException e) {
            logger.error("The DNI must be a number");
            logger.info("The program keeps working...");
        } finally {
            scanner.close();
        }
    }

    @Override
    public String toString() {
        return "\nStudent{" +
                "dni=" + getDni() +
                ", name='" + getName() + '\'' +
                ", lastname='" + getLastname() + '\'' +
                "averagemark=" + averagemark +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Person person = (Person) obj;
        if (getDni() != person.getDni()) {
            return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        return Objects.hash(getDni());
    }

    @Override
    public int getAge() {
        int age = Period.between(this.getBirthday(), LocalDate.now()).getYears();
        return age;
    }

    @Override
    public void setCountryResidence(String countryName){
        this.countryResidence = countryName;
    }

    @Override
    public String getCountryResidence(){
        String data = "";
        if(this.getName().equals("")){
            data += this.getName();
        }
        if (this.getCountryResidence() != null && !this.getCountryResidence().equals("Argentina")){
            if(data.equals("Argentina")){
                data = "This student does not have a country of residence";
            } else {
                data += "has not a country of residence define";
            }
        }
        return countryResidence;
    }


}

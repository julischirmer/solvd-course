package com.solvd.homework2;

import com.solvd.homework2.exceptions.AgeRestriction;
import com.solvd.homework2.exceptions.InvalidMailException;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public abstract class Person {

    private int dni;
    private String name;
    private String lastname;
    private String mail;
    private LocalDate birthday;


    public int getDni() {
        return dni;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }


    public final void setMail(String mail) throws InvalidMailException {
        if (mail.length() > 10) {
            this.mail = mail;
        } else {
            throw new InvalidMailException("The email must have more than 10 characters");
        }
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) throws AgeRestriction {
        int age = Period.between(birthday, LocalDate.now()).getYears();
        if (age >= 18) {
            this.birthday = birthday;
        } else {
            throw new AgeRestriction("You must have more than 18 years old");
        }
    }

    public abstract int getAge();

    @Override
    public String toString() {
        return "Person{" +
                "dni=" + dni +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Person person = (Person) obj;
        if (dni != person.dni) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}

package com.solvd.homework2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.function.Consumer;

public class Enrollment {
    final Logger logger = LogManager.getLogger(Enrollment.class.getName());
    private int idEnrollment;
    private Student student;
    private Course course;

    private int count = 0;
    public Enrollment(int id, Student student, Course course) {
        setIdEnrollment(id);
        setStudent(student);
        if(Course.isTheCourseAvailable(course)){
            setCourse(course);
            countStudentsEnroll();
        } else {
            logger.error("The course is not available");
        }

    }

    public int getIdEnrollment() {
        return idEnrollment;
    }

    public void setIdEnrollment(int idInscription) {
        this.idEnrollment = idInscription;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void countStudentsEnroll(){
        Consumer<Integer> amount = (x) -> count++;
        amount.accept(count);
    }


    @Override
    public String toString() {
        return "Enrollment{" +
                "idEnrollment=" + idEnrollment +
                ", student=" + student +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return idEnrollment == that.idEnrollment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEnrollment);
    }
}

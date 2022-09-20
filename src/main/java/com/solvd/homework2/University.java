package com.solvd.homework2;

import com.solvd.homework2.enums.Grade;
import com.solvd.homework2.functionalInterfaces.IDiscount;
import com.solvd.homework2.interfaces.ICalculateTotalCost;
import com.solvd.homework2.interfaces.ICountryResidence;
import com.solvd.homework2.interfaces.IDiscountScholarship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Objects;

public class University implements ICountryResidence, ICalculateTotalCost, IDiscountScholarship {
    public final Logger logger = LogManager.getLogger(University.class.getName());
    private String name;
    private String address;
    private String countryResidence;
    private LinkedList<Student> students = new LinkedList<>();
    private LinkedList<Professor> professors = new LinkedList<>();
    private LinkedList<Course> courses = new LinkedList<>();
    private LinkedList<Exam> exams = new LinkedList<>();

    public University(String name, String address) {
        this.setName(name);
        this.setAddress(address);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addProfessor(Professor professor){
        professors.add(professor);
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public void addExam (Exam exam){
        exams.add(exam);
    }

    public LinkedList<Subject> getSubjectsPerStudent(int dni){
        Student studentf = null;
        for(Student student : students){
            if(student.getDni()==dni){
                studentf = student;
            } else {
                logger.info("Student not found");
            }
        }
        for (Course course:courses){
            if(course.getStudents().contains(studentf)){
                return course.getSubjects();
            } else {
                logger.info("Subjects not found");
            }
        }
        return null;
    }
    public String getSpecialtyPerStudent(int dni){
        for(Student student:students){
            if (student.getDni()==dni){
                for(Course course : courses){
                    return course.getSpecialty().getName();
                }
            }
        }
        return null;
    }

    public void getGradesPerStudent(int dni){
        for (Exam exam : exams){
            if(exam.grades.containsKey(dni)){
                logger.info("The student: " + dni + " has grade: " + exam.grades.get(dni) +
                        " in the subject: " + exam.getSubject().getName() + " at the date: " + exam.getExamdate());
            }
        }
    }

    @Override
    public void setCountryResidence(String country) {
        this.countryResidence = countryResidence;
    }

    @Override
    public String getCountryResidence() {
        if(this.getCountryResidence().isEmpty()){
            return "The University hasn't a Country Residence";
        } else {
            return "The country of residence of the University is:" + this.getCountryResidence();
        }
    }


    @Override
    public double getTotalCost(Student student){
        double total_cost = 0;
        for(Course course : courses ){
            if(course.getStudents().contains(student)){
                total_cost = total_cost + course.getCost();
            }
        }
        return total_cost;
    }

    @Override
    public void DiscountScholarship(Student student, IDiscount discount){
        for(Student fstudent : students){
            if(fstudent.equals(student)){
                if (fstudent.getAverageMark()>9){
                    discount.goodAverageMarkDiscount();
                } else {
                    logger.info("You don't have an average mark greater than 9" +
                            "so you can't have an scholarship");
                }
            }
        }
    }

    @Override
    public String toString() {
        return "University{" +
                "name='" + name + '\'' +
                ", location='" + address + '\'' +
                ", countryResidence='" + countryResidence + '\'' +
                ", students=" + students +
                ", professors=" + professors +
                ", courses=" + courses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return Objects.equals(logger, that.logger) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(countryResidence, that.countryResidence) && Objects.equals(students, that.students) && Objects.equals(professors, that.professors) && Objects.equals(courses, that.courses) && Objects.equals(exams, that.exams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logger, name, address, countryResidence, students, professors, courses, exams);
    }
}

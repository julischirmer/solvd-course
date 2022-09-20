package com.solvd.homework2;

import com.solvd.homework2.enums.DepartmentType;
import com.solvd.homework2.enums.Grade;
import com.solvd.homework2.exceptions.InvalidCourseCostException;
import com.solvd.homework2.functionalInterfaces.IDiscount;
import com.solvd.homework2.functionalInterfaces.ISalaryBonus;
import com.solvd.homework2.generics.CustomLinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Core;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.function.*;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) throws InvalidCourseCostException {

        // Creating University
        University harvard = new University("Harvard", "Cambridge, Massachusetts");

        // Creating Students
        Student student1 = new Student(111, "Julian", 10);
        Student student2 = new Student(112, "Carlos", 7.40);

        // Creating Professors
        Professor professor1 = new Professor(2000, "Juan", "Perez", 1000);
        Professor professor2 = new Professor(2001, "Miguel", "Adriano", 1300);

        harvard.addStudent(student1);
        harvard.addStudent(student2);
        harvard.addProfessor(professor1);
        harvard.addProfessor(professor2);

        // Creating Departments
        Department law = new Department(001, DepartmentType.LAW);
        Department science = new Department(002, DepartmentType.SCIENCE);
        Department econ = new Department(003, DepartmentType.ECONOMIC);

        // Creating Specialties
        Specialty lawyer = new Specialty(22, "Lawyer", law);
        Specialty doctor = new Specialty(23, "Doctor", science);
        Specialty accountant = new Specialty(24, "Accountant", econ);

        // Creating Subjects
        Subject math = new Subject(001, "Mathematics 1", accountant);
        Subject neurology = new Subject(002, "Neurology", doctor);
        Subject history1 = new Subject(003, "History 1", lawyer);

        // Creating Courses
        Course europeanHistory = new Course(11, 500, "European History", lawyer);
        Course latamHistory = new Course(12, 500, "Latam History", lawyer);
        Course mathProffesor = new Course(13, 600, "Mathematics Professor", accountant);

        latamHistory.addSubject(history1);
        europeanHistory.addSubject(history1);
        mathProffesor.addSubject(math);

        harvard.addCourse(europeanHistory);
        harvard.addCourse(latamHistory);
        harvard.addCourse(mathProffesor);

        europeanHistory.addStudent(student1);
        latamHistory.addStudent(student1);

        logger.info("Cost education for student: " + harvard.getTotalCost(student1));

        // Creating Enrollments
        Enrollment enroll1 = new Enrollment(01, student1, europeanHistory);
        Enrollment enroll2 = new Enrollment(02, student2, europeanHistory);

        // Creating history exams
        Exam historyExam = new Exam(001, LocalDate.parse("2022-08-02"), history1);
        Exam historyExam2 = new Exam(002, LocalDate.parse("2022-09-02"), history1);

        // Add Students to each exam
        historyExam.addStudent(student1);
        historyExam2.addStudent(student2);


        historyExam.grades.put(student1.getDni(), Grade.FAIL);
        historyExam2.grades.put(student1.getDni(), Grade.PASS);


        Exam mathExam = new Exam(003, LocalDate.parse("2022-09-15"), math);
        mathExam.grades.put(student2.getDni(), Grade.DISTINCTION);

        harvard.addExam(historyExam);
        harvard.addExam(historyExam2);
        harvard.addExam(mathExam);

        // Getting grades per student dni
        harvard.getGradesPerStudent(111);
        harvard.getGradesPerStudent(112);

        // Subjects for student enroll
        logger.info("Subjects for student are: " + harvard.getSubjectsPerStudent(111));

        // Specialty student will learn
        logger.info("The specialty that student will learn is: " + harvard.getSpecialtyPerStudent(111));

        // Scholarship discount example
        IDiscount disc = () -> {
            logger.info("Congrats! You have more than 9 in your average mark");
            logger.info("You will have 25% of discount in your course");
            double total_cost = harvard.getTotalCost(student1);
            double newCost = total_cost * 0.75;
            logger.info("Your new cost is: " + newCost);
        };
        harvard.DiscountScholarship(student1, disc);

    }

}


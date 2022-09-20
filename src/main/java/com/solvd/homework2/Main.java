package com.solvd.homework2;

import com.solvd.homework2.enums.DepartmentType;
import com.solvd.homework2.enums.Grade;
import com.solvd.homework2.enums.UserAccess;
import com.solvd.homework2.exceptions.InvalidCourseCostException;
import com.solvd.homework2.functionalInterfaces.IDiscount;
import com.solvd.homework2.functionalInterfaces.ISalaryBonus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) throws InvalidCourseCostException {

        // Creating University
        University harvard = new University("Harvard", "Cambridge, Massachusetts");

        // Creating Students
        Student jschirmer = new Student(111, "Julian", "Schirmer", 10);
        Student cherrera = new Student(112, "Carlos","Herrera", 7.40);

        // Creating Professors
        Professor jperez = new Professor(2000, "Juan", "Perez", 1000);
        Professor madriano = new Professor(2001, "Miguel", "Adriano", 1300);


        harvard.addStudent(jschirmer);
        harvard.addStudent(cherrera);
        harvard.addProfessor(jperez);
        harvard.addProfessor(madriano);

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

        europeanHistory.addStudent(jschirmer);
        latamHistory.addStudent(jschirmer);

        logger.info("Cost education for student: " + harvard.getTotalCost(jschirmer));

        // Creating Enrollments
        Enrollment enroll1 = new Enrollment(01, jschirmer, europeanHistory);
        Enrollment enroll2 = new Enrollment(02, cherrera, europeanHistory);

        // Creating history exams
        Exam historyExam = new Exam(001, LocalDate.parse("2022-08-02"), history1);
        Exam historyExam2 = new Exam(002, LocalDate.parse("2022-09-02"), history1);

        // Add Students to each exam
        historyExam.addStudent(jschirmer);
        historyExam2.addStudent(cherrera);


        historyExam.grades.put(jschirmer.getDni(), Grade.FAIL);
        historyExam2.grades.put(jschirmer.getDni(), Grade.PASS);


        Exam mathExam = new Exam(003, LocalDate.parse("2022-09-15"), math);
        mathExam.grades.put(cherrera.getDni(), Grade.DISTINCTION);

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

        // Scholarship discount example - using lambda by parameter
        IDiscount disc = () -> {
            logger.info("Congrats! You have more than 9 in your average mark");
            logger.info("You will have 25% of discount in your course");
            double total_cost = harvard.getTotalCost(jschirmer);
            double newCost = total_cost * 0.75;
            logger.info("Your new cost is: " + newCost);
        };
        harvard.DiscountScholarship(jschirmer, disc);

        // Students in History 1 Exam
        historyExam.countStudents();

        // Creating Users
        User jschirmerUser = new User("jschirmer", "wndy2269", UserAccess.STUDENT);
        User cherreraUser = new User("cperez","pep45666", UserAccess.STUDENT);

        User jperezUser = new User("jperez", "hrt2976",UserAccess.PROFESSOR);
        User madrianoUser = new User("madriano", "typ5487", UserAccess.PROFESSOR);



    }

}


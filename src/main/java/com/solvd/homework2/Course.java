package com.solvd.homework2;

import com.solvd.homework2.exceptions.InvalidCourseCostException;
import com.solvd.homework2.enums.CourseAvailability;
import com.solvd.homework2.functionalInterfaces.IAvailability;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;


public class Course {

    public static final Logger logger = LogManager.getLogger(Course.class.getName());
    private final int total = 150;
    private int idCourse;
    private String name;
    private Specialty specialty;
    private LinkedList<Subject> subjects = new LinkedList<>();
    private LinkedList<Student> students = new LinkedList<>();
    private double cost;

    private CourseAvailability courseAvailability;

    public Course() {

    }

    public Course(int idcourse, double price, String name, Specialty specialty) throws InvalidCourseCostException {
        this.setIdCourse(idcourse);
        this.setName(name);
        this.setCost(price);
        this.setSpecialty(specialty);
    }

    public Course(int idCourse) {
        this.setIdCourse(idCourse);
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idcourse) {
        this.idCourse = idcourse;
    }

    public LinkedList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(LinkedList<Subject> subjects) {
        this.subjects = subjects;
    }

    public double getCost() {
        return cost;
    }
    public void setCost(double cost) throws InvalidCourseCostException {
        if (cost < 0) {
            throw new InvalidCourseCostException("The cost can't be negative");
        } else {
            this.cost = cost;
        }
    }


    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public int getAvailability(IAvailability<Integer,Integer> availability){
        return availability.availability(this.total, students.size());
    }

    public LinkedList<Student> getStudents() {
        return students;
    }

    public void setStudents(LinkedList<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student){
        students.add(student);
        int cant = this.total - students.size();
        if(cant > 1){
            this.courseAvailability = CourseAvailability.AVAILABLE;
        }else {
            this.courseAvailability = CourseAvailability.FULL;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addSubject(Subject subject) {
        this.subjects.addLast(subject);
    }

    public void removeCourse(Subject subject) {
        if(subjects.contains(subject)){
            this.subjects.remove(subject);
        } else {
            logger.info("Subject not found");
        }
    }

    public static boolean isTheCourseAvailable (Course course){
        IAvailability<Integer,Integer> availability =
                (totalquota, studentsEnroll) -> totalquota - studentsEnroll;
        if(course.getAvailability(availability)>1){
            return true;
        } else {
            return false;
        }
    }

    public void findCourse(LinkedList<Course> courses) {
        Scanner scanner = new Scanner(System.in);
        IAvailability<Integer,Integer> availability =
                (totalquota, studentsEnroll) -> totalquota - studentsEnroll;
        try {
            logger.info("Insert course ID");
            Course course = new Course(scanner.nextInt());
            if (courses.contains(course)) {
                Course coursefinded = courses.get(courses.indexOf(course));
                if (coursefinded.getAvailability(availability) > 0) {
                    logger.info("The Course is available");
                    logger.info("The course quota is: " + coursefinded.getAvailability(availability));
                    scanner.close();
                } else {
                    logger.info("The Course is completed");
                    scanner.close();
                }
            } else {
                logger.info("The id Course doesn't exist");
                scanner.close();
            }
        } catch (InputMismatchException e) {
            logger.error("The course id must be a number");
            logger.info("The program keeps working...");
        } finally {
            scanner.close();
        }

    }

    public void getCourseCost(LinkedList<Course> courses) {
        Scanner scanner = new Scanner(System.in);
        try {
            logger.info("Insert course ID");
            Course course = new Course(scanner.nextInt());
            if (courses.contains(course)) {
                Course coursefind = courses.get(courses.indexOf(course));
                logger.info("The course: " + coursefind.getName() + " has the cost: u$d " + coursefind.getCost());
            } else {
                logger.info("The course doesn't exist");
            }
        } catch (InputMismatchException e) {
            logger.error("The course id must be a number");
            logger.info("The program keeps working...");
        } finally {
            scanner.close();
        }
    }

    public void getCourseSubjects(LinkedList<Course> courses) {
        Scanner scanner = new Scanner(System.in);
        try {
            logger.info("Insert course ID");
            Course course = new Course(scanner.nextInt());
            if (courses.contains(course)) {
                Course coursefinded = courses.get(courses.indexOf(course));
                logger.info(coursefinded.getSubjects());
            } else {
                logger.info("The id Course doesn't exist");
            }
        } catch (InputMismatchException e) {
            logger.error("The course id must be a number");
            logger.info("The program keeps working...");
        } finally {
            scanner.close();
        }
    }

    public static void getCostCourse(Course course){
        Consumer<Course> consumer = (x) -> {
            logger.info("The course: " + x.getName() + " has a cost of : u$d " + x.getCost());
        };
        consumer.accept(course);
    }

    public static void isStudentEnroll(Student student, Course course){
        BiConsumer<Student, Course> biConsumer = (x, y) -> {
            if(y.getStudents().contains(x)){
                logger.info("The student: " + x.getName() + " " + x.getLastname() + " is enroll to: " + y.getName() + " course");
            } else {
                logger.info("The student is not enroll in any course");
            }
        };
        biConsumer.accept(student,course);
    }

    @Override
    public String toString() {
        return "Course{" +
                "idCourse=" + idCourse +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return idCourse == course.idCourse;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCourse);
    }


}

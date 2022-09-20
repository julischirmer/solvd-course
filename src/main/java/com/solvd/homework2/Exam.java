package com.solvd.homework2;


import com.solvd.homework2.enums.Grade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Exam {
    public final Logger logger = LogManager.getLogger(Exam.class.getName());
    private LocalDate examdate;
    private int examnumber;
    private LinkedList<Professor> professors = new LinkedList<>();
    private Subject subject;
    private LinkedList<Student> students = new LinkedList<>();
    Map<Integer,Grade> grades = new HashMap<>();

    public Exam(int examnumber, LocalDate examdate ,Subject subject) {
        this.setExamnumber(examnumber);
        this.setExamdate(examdate);
        this.setSubject(subject);
    }

    public LocalDate getExamdate() {
        return examdate;
    }

    public void setExamdate(LocalDate examdate) {
        this.examdate = examdate;
    }

    public int getExamnumber() {
        return examnumber;
    }

    public void setExamnumber(int examnumber) {
        this.examnumber = examnumber;
    }

    public LinkedList<Professor> getProfessors() {
        return professors;
    }

    public void addProffesor(Professor professor) {
        addProfessor.accept(professor);
    }

    public void setSubject (Subject subject){
        this.subject = subject;
    }

    public Subject getSubject(){
        return this.subject;
    }

    public LinkedList<Student> getStudents() {
        return students;
    }


/*    public void setGrade(Student student, Subject subject ,Grade grade){
        if(this.students.contains(student) && this.subject.equals(subject)){
            this.grade = grade;
        } else {
            logger.info("The student does not exist in the exam");
        }
    }*/

 /*   public Grade getGrade(){
        return this.grade;
    }*/

    Consumer<Professor> addProfessor = (Professor) -> {
        logger.info(Professor);
        professors.add(Professor);
    };

    Consumer<Student> addStudent = (Student) -> {
        logger.info(Student);
        students.addLast(Student);
    };

    public void addStudent(Student student){
        addStudent.accept(student);
    }

    public List<String> getStudentsNames (){
        return this.students.stream().map(student -> student.getName()).collect(Collectors.toList());
    }

    // STREAMS

    public void toLowerCaseNames(List<String> studentsnames){
        studentsnames.stream().map(x -> x.toLowerCase()).forEach(x -> logger.info(x));
    }

    public void countStudents(){
        long count = students.stream().count();
        logger.info("The quantity of students enroll for the exam are: " + count);
    }

    public void findName(List<String> studentsnames, String name){
        studentsnames.stream().filter(x -> x.equals(name)).forEach(x->logger.info(x));
    }

    public void sortedNamesAtoZ(List<String> studentsNames){
        studentsNames.stream().sorted().forEach(x->logger.info(x));
    }

    public void sortedNamesZtoA(List<String> studentsNames){
        studentsNames.stream().sorted((x,y)-> y.compareTo(x)).forEach(x->logger.info(x));
    }

    public void distinctNames(List<String> studentsnames){
        studentsnames.stream().distinct().forEach(x->logger.info(x));
    }


    @Override
    public String toString() {
        return "Exam{" +
                "logger=" + logger +
                ", examdate=" + examdate +
                ", examnumber=" + examnumber +
                ", professors=" + professors +
                ", subject=" + subject +
                ", students=" + students +
                ", grades=" + grades +
                ", addProfessor=" + addProfessor +
                ", addStudent=" + addStudent +
                '}';
    }
}

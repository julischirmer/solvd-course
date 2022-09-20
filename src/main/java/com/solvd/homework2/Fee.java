package com.solvd.homework2;


import com.solvd.homework2.exceptions.InvalidCourseCostException;
import com.solvd.homework2.exceptions.InvalidMonthNumberException;
import com.solvd.homework2.functionalInterfaces.IDiscount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Scanner;

public final class Fee {

    public final Logger logger = LogManager.getLogger(Fee.class.getName());

    private int month; // 0 January - 11 December
    private boolean isPay; // True = Pay the month fee & false = don't pay the month fee
    private Student student;
    private double costPerMonth;
    private Course course;
    public Fee(Student student, int month, boolean isPay, Course course) {
        try {
            this.setStudent(student);
            this.setMonthFee(month);
            this.setCourse(course);
        } catch (InvalidMonthNumberException e) {
            logger.info(e.getMessage());
        }
        this.setPay(isPay);
    }

    private void setCourse(Course course) {
        this.course = course;
    }

    public void isUpToday(Student student, Fee fee) {
        int monthNow = LocalDate.now().getMonthValue();
        if (fee.getMonthFee() == monthNow && fee.isPay == true) {
            logger.info("The student: " + student.getName() + " is up to date with the fee");
        } else {
            logger.info("The student: " + student.getName() + " is not up to date with the fee");
        }
    }


    public void createFee(Student student, Course course) throws InvalidMonthNumberException {
        Scanner scanner = new Scanner(System.in);
        try {
            logger.info("Insert student dni");
            int dni = scanner.nextInt();
            logger.info("Insert number month 0 January - 11 December");
            int month = scanner.nextInt();
            int pay = 2;
            boolean isPay = false;
            while (pay != 0 && pay != 1) {
                logger.info("is Pay? 1: true 0: false");
                pay = scanner.nextInt();
                if (pay == 0) {
                    isPay = false;
                } else if (pay == 1) {
                    isPay = true;
                } else {
                    logger.info("The answer must be 0 or 1");
                }
            }
        logger.info("Insert id course");
        int idcourse = scanner.nextInt();
        logger.info("Insert course cost");
        double price = scanner.nextDouble();
        logger.info("Insert course name");
        String name = scanner.next();
        Fee fee = new Fee(student,month, isPay,course);
        logger.info(fee);
        } catch (InvalidMonthNumberException e) {
            logger.error("The Month must be an int between 0 to 11");
        } finally {
            scanner.close();
        }
    }

    public int getMonthFee() {
        return month;
    }

    public void setMonthFee(int month) throws InvalidMonthNumberException {
        if (month >= 0 && month <= 11) {
            this.month = month;
        } else {
            throw new InvalidMonthNumberException("The month must be an integer between 0 January to 11 December");
        }

    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCostPerMonth(){

    }

    @Override
    public String toString() {
        return "Fee{" +
                "logger=" + logger +
                ", month=" + month +
                ", isPay=" + isPay +
                ", student=" + student +
                ", costPerMonth=" + costPerMonth +
                ", course=" + course +
                '}';
    }
}

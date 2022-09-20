package com.solvd.homework2.reflection;

import com.solvd.homework2.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Reflection {
    public static Logger logger = LogManager.getLogger(Reflection.class.getName());
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class reflectClass = Student.class;

        Field[] fields = reflectClass.getDeclaredFields();

        logger.info("\nFields inside Student class:\n");
        for (Field field : fields) {
            logger.info((field.getModifiers() == 0 ? "default " : field.getModifiers() == 1 ? "public " : "private ") +
                    field.getType() + " " + field.getName());
        }

        Method[] methods = reflectClass.getDeclaredMethods();

        logger.info("\nmethods inside Student class:\n");
        for (Method method : methods) {
            logger.info((method.getModifiers() == 0 ? "default " : method.getModifiers() == 1 ? "public " : "private ") +
                    method.getReturnType() + " " + method.getName() + (method.getParameterTypes().length == 0 ? "()" : "( " +
                    Arrays.toString(method.getParameterTypes()) + " )"));
        }
        Constructor[] constructors = reflectClass.getConstructors();

        Method declareMeth = reflectClass.getDeclaredMethod("findStudent", LinkedList.class);
        logger.info("Calling method using reflection: " + declareMeth.invoke(constructors[2].newInstance()));

        logger.info("\nConstructors in Student class:\n");
        for (Constructor constructor : constructors) {
            logger.info(constructor);
        }
    }

}

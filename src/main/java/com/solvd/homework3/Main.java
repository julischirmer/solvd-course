package com.solvd.homework3;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.File;
import java.io.IOException;


import java.util.*;

public class Main {

    private static Logger logger = LogManager.getLogger(com.solvd.homework2.Main.class.getName());
    private static File myfile = new File ("src/main/resources/text.txt");
    private static File myfile2 = new File ("src/main/resources/text2.txt");
    private  static File fileResult = new File ("src/main/resources/result.txt");

    public static void main(String[] args) throws IOException {

        String text = FileUtils.readFileToString(myfile, "UTF-8").toLowerCase();
        String text2 = FileUtils.readFileToString(myfile2,"UTF-8").toLowerCase();

        logger.info("The size of the file text.txt is:" + FileUtils.sizeOf(myfile) + " bytes");
        logger.info("The size of the file text2.txt is:" + FileUtils.sizeOf(myfile2) + " bytes");


        if(StringUtils.compare(text,text2) == 0){
            logger.info("Both text are equals");
        } else {
            logger.info("Both text are not equals");
        }

        if(StringUtils.isEmpty(text)){
            logger.info("The text is empty");
        } else {
            logger.info("The text is not empty");
        }

        String seqSearch = "java";
        if(StringUtils.contains(text,seqSearch )){
            logger.info("the text contains the word: "+seqSearch);
        } else {
            logger.info("the text does not contains the word: "+seqSearch);
        }


        HashSet<String> set = new HashSet<>(Arrays.asList(text.split("")));
        logger.info(text.split(" ").length + " words " + set.size() + " unique words");
        countUniqueWords(text);


    }

    public static void countUniqueWords(String text) throws IOException {

        int quantity = 0;
        String[] words = text.split("[$&+,:;=?@#|'<>.-^*()%!]| |[0-9]|\n", 0);

        for (String word : words) {
            if (!StringUtils.isBlank(word) && StringUtils.countMatches(text, word) == 1) {
                quantity++;
            }
        }

        FileUtils.writeStringToFile(fileResult, "Unique words in the text: " +
                quantity + " \n", "UTF-8", true);
        logger.info("Unique words : " + quantity);

    }



}

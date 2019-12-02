import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) {
        TaskHandler taskH = new TaskHandler("C:\\Chen\\BGU\\2020\\2020 - A\\Software Quality\\Assignments\\Assignment 1\\SoftwareQualityAss1\\src\\names_set.csv");
//        TaskHandler taskH = new TaskHandler("names.csv");  // somehow load it from src, i don't know how
//        taskH.countSpecificString("N");
//        taskH.countAllStrings(2);
        System.out.println(taskH.generateName());
        System.out.println(taskH.countMaxString(5));
        System.out.println(taskH.countMaxString(4));
        System.out.println(taskH.countMaxString(3));
        System.out.println(taskH.countMaxString(2));
        System.out.println(taskH.countMaxString(1));
        taskH.allIncludesString("chenyanainitsanjakenoa");
    }



}



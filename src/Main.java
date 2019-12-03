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
        TaskHandler taskH = new TaskHandler("names_set.csv");  // somehow load it from src, i don't know how

//        TaskHandler taskH = new TaskHandler("C:\\Chen\\BGU\\2020\\2020 - A\\Software Quality\\Assignments\\Assignment 1\\SoftwareQualityAss1\\src\\names_set.csv");
        if (args[0].equals("CountSpecificString")) {
            System.out.println(taskH.countSpecificString(args[1]));
        }
        else if (args[0].equals("CountAllStrings")) {
            taskH.countAllStrings(Integer.valueOf(args[1]));
        }
        else if (args[0].equals("CountMaxString")) {
            System.out.println(taskH.countMaxString(Integer.valueOf(args[1])));
        }
        else if (args[0].equals("AllIncludesString")) {
            taskH.allIncludesString(args[1]);
        } else if (args[0].equals("GenerateName")) {
            System.out.println(taskH.generateName());
        }


    }
}



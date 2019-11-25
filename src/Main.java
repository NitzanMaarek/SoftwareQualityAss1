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
        TaskHandler taskH = new TaskHandler("C:\\Users\\nitsa\\Desktop\\Files for University Assignments\\names.csv");
//        TaskHandler taskH = new TaskHandler("names.csv");  // somehow load it from src, i don't know how
        taskH.countSpecificString("N");
        taskH.countAllStrings(1);
    }



}



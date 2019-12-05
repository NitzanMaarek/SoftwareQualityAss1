import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) {
        TaskHandler taskH = new TaskHandler("names_set.csv");  // somehow load it from src, i don't know how

//        Main.TaskHandler taskH = new Main.TaskHandler("C:\\Chen\\BGU\\2020\\2020 - A\\Software Quality\\Assignments\\Assignment 1\\SoftwareQualityAss1\\src\\names_set.csv");
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


    public static class TaskHandler {

        private ArrayList<String> names;

        public TaskHandler(String path){
            this.names = new ArrayList<>();
            InputStream resourceStream = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(resourceStream))) {
                String new_line = "";
                while ((new_line = br.readLine()) != null) {
                    String[] values = new_line.split(",");
                    for(String value : values){
                        value = removeSignFromString(value);
                        this.names.add(value);
                    }
                    //                data.add(values);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private String removeSignFromString(String aString){
            String[] signs = {"[", "]", "'", "\""};
            StringBuilder sb = new StringBuilder(aString);
            for(String sign : signs){
                if(aString.contains(sign)){
                    aString = aString.replace(sign, "");
                }
            }
            return aString;
        }

        /**
         * Method counts and prints the number of times a string appears in all names.
         * @param aString
         */
        public int countSpecificString(String aString){
            int count = 0;
            for(String aName : names){
                int occurances = 0;
                occurances = countHowManyTimesSubstringInString(aName, aString);
                count = count + occurances;
            }
            return count;
        }

        /**
         * Returns the number of times substring appears in string.
         */
        private int countHowManyTimesSubstringInString(String theString, String theSubstring){
            int lastIndex = 0;
            int count = 0;

            while(lastIndex != -1){

                lastIndex = theString.indexOf(theSubstring,lastIndex);

                if(lastIndex != -1){
                    count ++;
                    lastIndex += theSubstring.length();
                }
            }
            return count;
        }

        /**
         * Counts and prints the appearances of each substring in length: length
         * @param length - the length of the substring.
         */
        public void countAllStrings(int length){
            Map<String, Integer> substringToCountMap = countStrings(length);
            for(String substring : substringToCountMap.keySet()){
                System.out.println(substring + ":" + substringToCountMap.get(substring).toString());
            }
        }

        public String countMaxString(int length){
            Map<String, Integer> substringToCountMap = countStrings(length);
            Integer maxValue = 0;
            String maxString = "";
            for (Map.Entry<String, Integer> entry : substringToCountMap.entrySet())
                {
                    if (entry.getValue() > maxValue)
                    {
                        maxValue = entry.getValue();
                        maxString = entry.getKey();
                    }
                }
            return maxString;
        }

        private Map<String, Integer> countStrings(int length) {
            Map<String, Integer> substringToCountMap = new HashMap<>();
            for (String name : this.names) {
                for (int i = 0; i + length <= name.length(); i++) {
                    String currentSubstring = name.substring(i, i + length);
                    if (substringToCountMap.containsKey(currentSubstring)) {
                        substringToCountMap.put(currentSubstring, substringToCountMap.get(currentSubstring) + 1);
                    } else {
                        substringToCountMap.put(currentSubstring, 1);
                    }
                }
            }
            return substringToCountMap;
        }

        public void allIncludesString(String aString){
            for (int i = 1; i < aString.length(); i++) {
                for (int j = 0; j + i <= aString.length(); j++) {
                    String subStr = aString.substring(j, j + i);
                    subStr = Character.toUpperCase(subStr.charAt(0)) + subStr.substring(1, subStr.length());
                    if (this.names.contains(subStr)) {
                        System.out.println(subStr.toLowerCase());
                    }
                }
            }

        }


        private int indexOf(char[] aCharArray, char aChar){
            for(int i = 0; i < aCharArray.length; i++){
                if(aCharArray[i] == aChar){
                    return i;
                }
            }
            return -1;
        }


        /**
         * Generates name according to each letters conditional probability.
         * Conditional probability according to previous char.
         * @return generated name.
         */
        public String generateName(){
            String generatedName = "";
            generatedName = generatedName + findMostOccurdCapitalLetter();
            int index = 1;
            while(index < 6){
                char nameLastChar = generatedName.charAt(generatedName.length() - 1);
                char nextChar = findNextChar(nameLastChar);
                String newGeneratedName = generatedName + nextChar;
    //            if(newGeneratedName.equals(generatedName)){
    //                generatedName = newGeneratedName;
    //                return generatedName;
    //            }
                index++;
                generatedName = newGeneratedName;
            }
            return generatedName;
        }

        /**
         * Returns the char with most appearances after given substring.
         * @param givenChar
         * @return character
         */
        private char findNextChar(char givenChar){
            char[] allPossibleChars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                    'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                    'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' '};
            int[] allPossibleCharsCounters = new int[53];
            int totalEncounters = 0;    // Number of encounters of givenChar
            int lastCharEncounters = 0; // Number of encounter givenChar was last char of a name.
            for(String name : this.names){
                for(int i=1; i < name.length(); i++){
                    char alpha = name.charAt(i);
                    if(name.charAt(i-1) == givenChar){
                        totalEncounters++;
                        int currentCharIndex = indexOf(allPossibleChars, alpha);
                        allPossibleCharsCounters[currentCharIndex] = allPossibleCharsCounters[currentCharIndex] + 1;
                    }
                    if((i == name.length() - 1) && (name.charAt(i) == givenChar)){
                        lastCharEncounters++;
                    }
                }
            }
            float prob = (float)lastCharEncounters / (float)totalEncounters;
            if(prob > 0.5){
                return Character.MIN_VALUE;
            }
            int maxCounter = 0;
            int maxIndex = 0;
            for(int i=0; i < allPossibleCharsCounters.length; i++){
                if(allPossibleCharsCounters[i] > maxCounter){
                    maxIndex = i;
                    maxCounter = allPossibleCharsCounters[i];
                }
            }
            return allPossibleChars[maxIndex];
        }

        private String findMostOccurdCapitalLetter(){
            String[] alphaUpperCase = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
                    "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
            int max_count = 0;
            String max_string = "";
            for(String alpha : alphaUpperCase){
                int current_count = countSpecificString(alpha);
                if(current_count > max_count){
                    max_count = current_count;
                    max_string = alpha;
                }

            }
            return max_string;
        }
        /*

        If generation of name would use conditional probability over prefix and not one char.

        public String generateName(){
            String generatedName = "";
            generatedName = generatedName + findMostOccurdCapitalLetter();
            while(true){
                String newGeneratedName = findNextSubstring(generatedName);
                if(newGeneratedName.equals(generatedName)){
                    generatedName = newGeneratedName;
                    break;
                }
                generatedName = newGeneratedName;
            }
            return generatedName;
        }

        private String findNextSubstring(String subString){
            char[] alphaLowerCase = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                    'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
            int[] alphaLowerCaseCounter = new int[26]; // 26 because there are 26 letters in the neglish gpalhabet.
            int totalsubStringEncounters = 0;
            int totalNextCharEncounters = 0;
            for(String name : this.names){
                if(name.length() >= subString.length() && name.substring(0,subString.length()).equals(subString)){
                    totalsubStringEncounters = totalsubStringEncounters + 1;
                    if(name.length() > subString.length()) {
                        char nextChar = name.charAt(subString.length());
                        int index = indexOf(alphaLowerCase, nextChar);
                        try {
                            alphaLowerCaseCounter[index] = alphaLowerCaseCounter[index] + 1;
                        }
                        catch (Exception e){
                            System.out.println(e.toString());
                        }
                        totalNextCharEncounters = totalNextCharEncounters + 1;
                    }

                }
            }
            float prob = (float)totalNextCharEncounters/(float)totalsubStringEncounters;
            if( prob > 0.5){
                int max = 0;
                int maxIndex = 0;
                for(int i=0; i < alphaLowerCaseCounter.length; i++){
                    if(alphaLowerCaseCounter[i] > max){
                        max = alphaLowerCaseCounter[i];
                        maxIndex = i;
                    }
                }
                return subString + alphaLowerCase[maxIndex];
            }
            return subString;
        }

        private String findMostOccurdCapitalLetter(){
            String[] alphaUpperCase = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
                    "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
            int max_count = 0;
            String max_string = "";
            for(String alpha : alphaUpperCase){
                int current_count = countSpecificString(alpha);
                if(current_count > max_count){
                    max_count = current_count;
                    max_string = alpha;
                }

            }
            return max_string;
        }

    */


    }
}



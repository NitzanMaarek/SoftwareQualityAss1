import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TaskHandler {

    private ArrayList<String> names;

    public TaskHandler(String path){
        names = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String new_line = "";
            while ((new_line = br.readLine()) != null) {
                String[] values = new_line.split(",");
                for(String value : values){
                    value = removeSignFromString(value);
                    names.add(value);
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
        String[] signs = {" ", "[", "]", "'", "\""};
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

    public void countAllStrings(int length){
        Map<String, Integer> substringToCountMap = new HashMap<>();
        for(String name: this.names){
            for(int i=0; i + length <= name.length(); i++){
                String currentSubstring = name.substring(i, i + length);
                if(substringToCountMap.containsKey(currentSubstring)){
                    substringToCountMap.put(currentSubstring, substringToCountMap.get(currentSubstring) + 1);
                }
                else{
                    substringToCountMap.put(currentSubstring, 1);
                }
            }
        }
        for(String substring : substringToCountMap.keySet()){
            System.out.println(substring + ":" + substringToCountMap.get(substring).toString());
        }
    }
    public int countMaxString(int length){
        return -1;
    }
    public int allIncludesString(String aString){
        return -1;
    }

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

    private int indexOf(char[] aCharArray, char aChar){
        for(int i = 0; i < aCharArray.length; i++){
            if(aCharArray[i] == aChar){
                return i;
            }
        }
        return -1;
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




}

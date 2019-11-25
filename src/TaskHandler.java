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
    public void countSpecificString(String aString){
        int count = 0;
        for(String aName : names){
            int occurances = 0;
            occurances = countHowManyTimesSubstringInString(aName, aString);
            count = count + occurances;
        }
        System.out.println(count);
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
        return "kaka";
    }




}

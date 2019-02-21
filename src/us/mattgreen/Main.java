package us.mattgreen;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {

    private final static FileInput indata = new FileInput("the_book.csv");
    private final static Map<String, Integer> map = new HashMap<String, Integer>();

    
    
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        String line;
        String[] words;

        while ((line = indata.fileReadLine()) != null) {
            // Remove anything that's not a letter or space
            line = line.replaceAll("[^a-zA-Z ]","")
                    .toLowerCase().trim();
           
            // Don't process lines with no characters
            if (line.isEmpty()) {
                continue;
            }
            
            // Split string over one or more spaces
            words = line.split(" +");
            
            // Look for each word in the map
            for (String word : words) {
                // This word isn't yet a key, init count to 1
                if (!map.containsKey(word)) {
                    map.put(word, 1);
                }
                else {
                    // Increment count using word as key
                    map.put(word, map.get(word) + 1);
                }

            } 
        }
        Map<String, Integer> newMap = sortByValue(map); 
        int a = 0;
        System.out.println("The Top ten most used words in the file: ");
            for (Map.Entry<String, Integer> entry : newMap.entrySet()) {
                if(a < 10){
                System.out.println(entry.getKey() + " " + entry.getValue());
                }
                a++;
            }
            
        System.out.println("All the words that were only used once in the file: ");
        for (Map.Entry<String, Integer> entry : newMap.entrySet()) {
                if(entry.getValue() == 1){
                System.out.println(entry.getKey() + " " + entry.getValue());
                }
            }
        } 
    // code found here. https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/ 
    // Slightly modified for functionality
    private Map<String, Integer> sortByValue(Map<String, Integer> map) {
          
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Integer> > list = 
               new LinkedList<Map.Entry<String, Integer> >(map.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                return (o2.getValue()).compareTo(o1.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    }
}
    

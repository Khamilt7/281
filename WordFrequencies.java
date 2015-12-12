import java.util.*;
import java.io.*;
public class WordFrequencies {
    public static void main (String[] args) {
        boolean itsClean = false;
        boolean itsSensitve = false;
        HashSet<String> hashSet = new HashSet<>(); 
        HashMap<String, Integer> hashMap = new HashMap<>();  
        Scanner scan = new Scanner(System.in).useDelimiter("[^a-zA-Z0-9\\-]");
        while(scan.hasNext()) {
            String last = scan.next();
            last.replaceAll("-", "");
            if(!(last.equals(""))) {
                last = itsSensitve ? last: last.toUpperCase();
                Integer keys = hashMap.get(last);
                hashMap.put(last, hashMap.containsKey(last) ? 1 + keys : 1);
                hashSet.add(last);
            }
        }
        if(args.length > 0) {
            if(args[0].equals("-c")) {
                itsClean = true;
            }
            if(args[0].equals("-s")) {
                itsSensitve = true;
            }
            if(args[0].equals("-sc") || args[0].equals("-cs")) {
                itsSensitve = true;
                itsClean = true;
            } 
        }  
        ArrayList<String> alpha = new ArrayList<String>(hashSet);
        Collections.sort(alpha);
        
        for(String printOut : alpha) {
            System.out.println(printOut + (itsClean ? "" : " " + hashMap.get(printOut)));
        }
    }
}

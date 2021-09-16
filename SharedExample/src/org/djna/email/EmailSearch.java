package org.djna.email;

import java.io.IOException;
import java.util.Map;

public class EmailSearch {

    public static void main(String[] args) {
        String fileName = "SharedExample/sample.txt";
        if (args.length > 1){
            fileName = args[1];
        }
        try {
            String regex = "\\S+@((?:\\w+\\.)+\\w+)\\s*";
            Map<String, Integer> domainMap = Helpers.searchFileWithRegex(fileName, regex);
            Helpers.printResults(Helpers.sortMap(domainMap));
            System.out.printf("Found %s %d times", regex, Helpers.getTotalDomains(domainMap));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

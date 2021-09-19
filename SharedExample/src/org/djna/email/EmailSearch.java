package org.djna.email;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmailSearch {

    public static void main(String[] args) {
        String fileName = "SharedExample/sample.txt";
        if (args.length > 1){
            fileName = args[1];
        }
        Path filePath = Paths.get(fileName);
        String contents = null;
        try {
            contents = Files.readString(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        searchFile(contents);

    }

    private static void searchFile(String contents) {

        String regex = "\\S+@((?:\\w+\\.)+\\w+)\\s*";
        Pattern emailPattern = Pattern.compile(regex);
        Matcher m = emailPattern.matcher(contents);

        int matchCount = 0;
        Set<String> uniqueDomains = new HashSet<>();
        while( m.find() ) {
            String domain = m.group(1);
            uniqueDomains.add(domain);
            System.out.println("domain " + domain);
            matchCount++;
        }

        System.out.println("Found " + matchCount);
        System.out.println("Count of unique domains " + uniqueDomains.size());

        List<String> sortedDomains = uniqueDomains.stream().sorted().collect(Collectors.toList());
        System.out.println("Sorted List of domains ");
        for ( String domain: sortedDomains){
            System.out.println("domain " + domain);
        }





    }


}

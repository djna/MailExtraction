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
        Map<String, Domain> domainCountMap = new HashMap<>();
        while( m.find() ) {
            String domainName = m.group(1);
            Domain domain = domainCountMap.getOrDefault(domainName, new Domain(domainName) );
            domain.incrementCount();
            domainCountMap.put(domainName, domain);
        }

        System.out.println("Count of unique domains " + domainCountMap.size());

        System.out.println("Domains sorted by name");
        domainCountMap.keySet().stream().forEachOrdered(
                     domainKey -> System.out.printf("domain %s %n", domainCountMap.get(domainKey) )
        );

        System.out.printf("%nDomains sorted by name%n");
        domainCountMap.values().stream().sorted(new Comparator<Domain>() {
            @Override
            public int compare(Domain leftDomain, Domain rightDomain) {
                if ( leftDomain.getCount() == rightDomain.getCount() ){
                    return leftDomain.getName().compareTo(rightDomain.getName());
                } else {
                    return (leftDomain.getCount() > rightDomain.getCount() ) ? 1 : -1 ;
                }
            }
        }).forEach(
                domain -> System.out.printf("domain %s %n", domain )
        );

    }


}

package org.djna.email;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailSearch {

    public static void main(String[] args) {
        String fileName = "SharedExample/sample.txt";
        if (args.length > 1){
            fileName = args[1];
        }
        try {
            searchFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchFile(String fileName) throws IOException {
        Path filePath = Paths.get(fileName);

        int counter = 0;

        String contents = Files.readString(filePath);

        String regex = "\\S+@((?:\\w+\\.)+\\w+)\\s*";
        Pattern emailPattern = Pattern.compile(regex);
        Matcher m = emailPattern.matcher(contents);

        Map<String, Integer> domainMap = new HashMap<String, Integer>();
        while( m.find() ) {
            String domain = m.group(1);
            Integer domainCount = domainMap.get(domain);
            if ( domainCount == null){
                domainCount = 0;
            }
            domainCount++;
            domainMap.put(domain, domainCount);
            counter++;
        }

        for ( String domain: domainMap.keySet() ){
            StringBuffer stringBuffer = new StringBuffer("DomainName ");
            stringBuffer.append(domain);
            stringBuffer.append(": ");
            stringBuffer.append(domainMap.get(domain));
            System.out.println(stringBuffer.toString());
        }

        System.out.printf("Found %s %d times", regex, counter);

    }


}

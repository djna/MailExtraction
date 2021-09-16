package org.djna.email;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {

    public static Map<String, Integer> searchFileWithRegex(String fileName, String regex) throws IOException {
        Path filePath = Paths.get(fileName);

        String contents = Files.readString(filePath);

        Pattern emailPattern = Pattern.compile(regex);
        Matcher m = emailPattern.matcher(contents);

        Map<String, Integer> domainMap = new LinkedHashMap<String, Integer>();
        while( m.find() ) {
            String domain = m.group(1);
            Integer domainCount = domainMap.get(domain);
            if ( domainCount == null){
                domainCount = 0;
            }
            domainCount++;
            domainMap.put(domain, domainCount);
        }
        return domainMap;
    }

    public static void printResults(Map<String, Integer> domainMap) {
        for ( String domain: domainMap.keySet() ){
            StringBuffer stringBuffer = new StringBuffer("Domain ");
            stringBuffer.append(domain);
            stringBuffer.append(": ");
            stringBuffer.append(domainMap.get(domain));
            System.out.println(stringBuffer);
        }
    }

    public static Map<String, Integer> sortMap(Map<String, Integer> domainMap) {
        Map<String, Integer> sortedMap = new LinkedHashMap<>();

        domainMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        return sortedMap;
    }

    public static Integer getTotalDomains(Map<String, Integer> domainMap) {
        Integer total = 0;
        for (Integer count : domainMap.values()) {
            total += count;
        }
        return total;
    }
}

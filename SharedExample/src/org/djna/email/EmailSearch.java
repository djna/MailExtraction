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

        String regex = "softwire.com";
        Pattern emailPattern = Pattern.compile(regex);
        Matcher m = emailPattern.matcher(contents);

        Map<String, Integer> domainMap = new HashMap<String, Integer>();
        while( m.find() ) {
            System.out.println("got " + m);
        }


    }


}

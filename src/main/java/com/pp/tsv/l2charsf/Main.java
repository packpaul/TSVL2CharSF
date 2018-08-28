/**
 * Copyright 2018 by Pavel Perminov (packpaul@mail.ru)
 */
package com.pp.tsv.l2charsf;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Main class of TSV longest 2 char substring finder.
 * <br><br>
 * Usage:
 * <ol>
 *   <li><code>java -jar l2charsf.jar [--help]</code> - shows help.
 *   <li><code>java -jar l2charsf.jar &lt;string&gt;</code> - finds longest 2 char substring in string.
 *   <li><code>java -jar l2charsf.jar --file &lt;file with string&gt;</code> -
 *       finds longest 2 char substring in string from a text file.
 * </ol>
 * 
 * @author Pavel Perminov (packpaul@mail.ru)
 */
public class Main {
    
    public static void main(String[] args)
    {
        if ((args.length == 0) || "--help".equals(args[0])) {
            showHelp();
            System.exit(0);
        }
        
        if ("--file".equals(args[0])) {
            if (args.length != 2) {
                showHelp();
                System.exit(0);
            }
            try(Stream<String> strings = Files.lines(Paths.get(args[1]))) {
                Longest2CharSubstringFinder finder = new Longest2CharSubstringFinder();
                for (Iterator<String> ei = strings.iterator(); ei.hasNext(); ) {
                    finder.evaluate(ei.next());
                }
                String result = finder.buildResult();
                System.out.println(
                        String.format("The longest 2 char substring of length %d is:\n%s",
                                      result.length(), result.toString()));
                System.exit(0);                
            } catch(Exception ex) {
                ex.printStackTrace();
                System.exit(-1);
            }
        }
        
        if (args.length != 1) {
            showHelp();
            System.exit(0);
        }

        try {
            String result = Longest2CharSubstringFinder.find(args[0]);
            System.out.println(
                    String.format("The longest 2 char substring of length %d is:\n%s",
                                  result.length(), result.toString()));;
            System.exit(0);                
        } catch(Exception ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

    }

    private static void showHelp() {
        System.out.println("java -jar 2charlsf.jar [--help] - shows help.");
        System.out.println("java -jar 2charlsf.jar <string> - finds 2 chars substring in the string.");
        System.out.println("java -jar calculator.jar --file <file with string> - " +
                               "finds 2 chars substring in the string from a text file.");
    }
    
}

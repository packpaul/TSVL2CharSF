/**
 * Copyright 2018 by Pavel Perminov (packpaul@mail.ru)
 */
package com.pp.tsv.l2charsf;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class that finds a longest substring containing only chars from a set of 2 different chars.
 * If there are several substring with the same length the first one is returned.
 * 
 * @author Pavel Perminov (packpaul@mail.ru)
 */
public class Longest2CharSubstringFinder {
    
    private Queue<Character> charQueue = new LinkedList<>();
    
    private Character char1;
    private int char1Count;
    
    private Character char2;
    private int char2Count;
    
    private String maxSubstring = "";
    
    public static String find(String string) {
        return new Longest2CharSubstringFinder().evaluate(string).buildResult();
    }

    public Longest2CharSubstringFinder evaluate(String string) {
        for (int i = 0; i < string.length(); i++) {
            Character ch = string.charAt(i);
            if ((char1 == null) || (ch.equals(char1))) {
                char1 = ch;
                char1Count++;
                charQueue.offer(ch);
            } else if ((char2 == null) || (ch.equals(char2))) {
                char2 = ch;
                char2Count++;
                charQueue.offer(ch);
            } else {
                updateSubstring();
                
                // discharge the queue to have only equal characters in it

                while (! charQueue.isEmpty()) {
                    ch = charQueue.poll();
                    if (ch.equals(char1)) {
                        if (--char1Count == 0) {
                            char1 = null;
                            break;
                        }
                    } else if (ch.equals(char2)) {
                        if (--char2Count == 0) {
                            char2 = null;
                            break;
                        }
                    } 
                }
                i--;
            }
        }
        
        updateSubstring();
        
        return this;
    }
    
    private void updateSubstring() {
        
        if ((char1Count == 0) || (char2Count == 0)) {
            return;
        }
        if (charQueue.size() <= maxSubstring.length()) {
            return;
        }
        
        maxSubstring = charQueue.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
    
    public String buildResult() {
        return maxSubstring;
    }
    
}
/**
 * Copyright 2018 by Pavel Perminov (packpaul@mail.ru)
 */
package com.pp.tsv.l2charsf;

/**
 * Class that finds a longest substring containing only chars from a set of 2 different chars.
 * If there are several substring with the same length the first one is returned.
 * 
 * @author Pavel Perminov (packpaul@mail.ru)
 */
public class Longest2CharSubstringFinder {
    
    private String string;
    
    private int queueHead = 0, queueTail = 0;
    
    private static final char EMPTY_CHAR = 0x0;
    
    private char char1 = EMPTY_CHAR;
    private int char1Count;
    
    private char char2 = EMPTY_CHAR;
    private int char2Count;
    
    private String maxSubstring = "";
    
    public static String find(String string) {
        return new Longest2CharSubstringFinder().evaluate(string).buildResult();
    }

    public Longest2CharSubstringFinder evaluate(String string) {
        
        this.string = string;
        
        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            if ((char1 == EMPTY_CHAR) || (ch == char1)) {
                char1 = ch;
                char1Count++;
                queueTail = i + 1;
            } else if ((char2 == EMPTY_CHAR) || (ch == char2)) {
                char2 = ch;
                char2Count++;
                queueTail = i + 1;
            } else {
                updateSubstring();
                
                // discharge the queue to have only equal characters in it

                while (queueTail - queueHead >= 0) {
                    ch = string.charAt(queueHead++);
                    if (ch == char1) {
                        if (--char1Count == 0) {
                            char1 = EMPTY_CHAR;
                            break;
                        }
                    } else if (ch == char2) {
                        if (--char2Count == 0) {
                            char2 = EMPTY_CHAR;
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
        if (queueTail - queueHead <= maxSubstring.length()) {
            return;
        }
        
        maxSubstring = string.substring(queueHead, queueTail);
        
    }
    
    public String buildResult() {
        return maxSubstring;
    }
    
}
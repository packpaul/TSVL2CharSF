/**
 * Copyright 2018 by Pavel Perminov (packpaul@mail.ru)
 */
package com.pp.tsv.l2charsf;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test cases for {@link Longest2CharSubstringFinder}
 * 
 * @author Pavel Perminov (packpaul@mail.ru)
 */
public class Longest2CharSubstringFinderTest {
    
    @Test
    public void testEmptyString() {
        
        final String string = "";
        
        String substring = Longest2CharSubstringFinder.find(string);
        
        assertEquals("", substring);
    }
    
    @Test
    public void testOneCharString() {
        
        final String string = "a";
        
        String substring = Longest2CharSubstringFinder.find(string);
        
        assertEquals("", substring);
    }
    
    @Test
    public void test2DiffCharString() {
        
        final String string = "ab";
        
        String substring = Longest2CharSubstringFinder.find(string);
        
        assertEquals("ab", substring);
    }
    
    @Test
    public void test3DiffCharString() {
        
        final String string = "abc";
        
        String substring = Longest2CharSubstringFinder.find(string);
        
        assertEquals("ab", substring);
    }
    
    @Test
    public void test3DiffCharStringLen4() {
        
        final String string = "abcc";
        
        String substring = Longest2CharSubstringFinder.find(string);
        
        assertEquals("bcc", substring);
    }
    
    @Test
    public void test3DiffCharStringLen21() {
        
        final String string = "aaabbbcababababababac";
        
        String substring = Longest2CharSubstringFinder.find(string);
        
        assertEquals("ababababababa", substring);
    }
    
}

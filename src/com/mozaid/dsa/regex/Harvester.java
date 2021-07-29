package com.mozaid.dsa.regex;

import edu.princeton.cs.algs4.In;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Print all substrings of input that match a RE.
 */
public class Harvester {

    public static void main(String[] args) {
        String regex = args[0];
        In in = new In(args[1]);
        String text = in.readAll();
        Pattern pattern = Pattern.compile(regex);   // compile() creates a Pattern (NFA) from RE.
        Matcher matcher = pattern.matcher(text);    // matcher() creates a Matcher (NFA simulator) from NFA and text.
        while (matcher.find()) {                    // find() looks for the next match.
            System.out.println(matcher.group());    // group() returns the substring most recently found by find().
        }
    }
}

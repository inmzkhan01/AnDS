package com.mozaid.dsa.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static void main(String[] args) {
        NFA nfa = new NFA(args[0]);
        System.out.println("Recognized by NFA: " + nfa.recognizes(args[1]));

        Pattern pattern = Pattern.compile(args[0]);
        Matcher matcher = pattern.matcher(args[1]);
        System.out.println("Recognized by Matcher: " + matcher.matches());
    }
    
}

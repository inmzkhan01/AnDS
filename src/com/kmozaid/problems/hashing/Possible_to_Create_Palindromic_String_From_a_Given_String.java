package com.kmozaid.problems.hashing;

import java.util.HashMap;

/**
 * write a function which takes a string s as input, and
 * returns true if the characters in s can be permuted to form a string that is palindromic,
 * i.e., reads the same backwards as forwards. For example, your function should return
 * true for “GATTAACAG”, since “GATACATAG” is a permutation of this string and is
 * palindromic
 */
public class Possible_to_Create_Palindromic_String_From_a_Given_String {

    public static boolean isPalindromPossible(String s) {
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (Character c : s.toCharArray()) {
            charCount.compute(c, (k, v) -> v == null ? 1 : (v + 1));
        }

        boolean isOddSeen = false;
        for (int count : charCount.values()) {

            if (isOddSeen && count % 2 != 0)
                return false;

            if (!isOddSeen && count % 2 != 0)
                isOddSeen = true;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindromPossible("GATTAACAG"));
        System.out.println(isPalindromPossible("aaba"));
    }
}

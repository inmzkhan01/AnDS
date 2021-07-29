package com.kmozaid.problems.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Can_Make_Word_Using_Dice {


    public static boolean canMake(char[][] dices, String word) {
        List<String> result = new ArrayList<>();
        List<Integer> visitedDices = new ArrayList<>();
        return canMake(dices, word.toCharArray(), 0, visitedDices, result);
    }

    private static boolean canMake(char[][] dices, char[] word, int length, List<Integer> visitedDices, List<String> result) {
        if (length == word.length) {
            System.out.println(result);
            return true;
        }

        for (int i = 0; i < dices.length; i++) {
            for (int j = 0; j < dices[0].length; j++) {
                if (!visitedDices.contains(i) && dices[i][j] == word[length]) {
                    visitedDices.add(i);

                    String diceAndFace = (i + 1) + " : " + (j + 1);
                    result.add(diceAndFace);

                    if (canMake(dices, word, length + 1, visitedDices, result)) {
                        return true;
                    }

                    //Backtrack

                    //visitedDices.remove(i);
                    visitedDices.remove(visitedDices.size() - 1);

                    //result.remove(diceAndFace);
                    result.remove(result.size() - 1);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        char[][] dices = {
                {'a', 'l', 'c', 'd', 'e', 'f'},
                {'a', 'b', 'c', 'd', 'e', 'f'},
                {'a', 'b', 'c', 'h', 'e', 'f'},
                {'a', 'b', 'c', 'd', 'o', 'f'},
                {'a', 'b', 'c', 'l', 'e', 'f'}
        };

        System.out.println(canMake(dices, "hello"));
    }
}

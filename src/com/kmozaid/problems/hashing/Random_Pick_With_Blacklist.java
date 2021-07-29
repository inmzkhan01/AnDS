package com.kmozaid.problems.hashing;

import java.util.*;

//https://leetcode.com/problems/random-pick-with-blacklist
public class Random_Pick_With_Blacklist {

    Map<Integer, Integer> blackListMap;
    Random random;
    int randomBound;

    public Random_Pick_With_Blacklist(int N, int[] blacklist) {
        random = new Random();
        randomBound = N - blacklist.length;

        Set<Integer> whiteListNums = new HashSet<>();
        for (int i = randomBound; i < N; i++) {
            whiteListNums.add(i);
        }
        for (int b : blacklist) {
            whiteListNums.remove(b);
        }

        blackListMap = new HashMap<>();

        Iterator<Integer> whiteListNumsItr = whiteListNums.iterator();
        for (int b : blacklist) {
            if (b < randomBound) {
                blackListMap.put(b, whiteListNumsItr.next());
            }
        }


    }

    public int pick() {
        int number = random.nextInt(randomBound);
        return blackListMap.getOrDefault(number, number);
    }

    public static void main(String[] args) {
        Random_Pick_With_Blacklist randomPick = new Random_Pick_With_Blacklist(8, new int[]{1, 7});
        int i = 0;
        while (i++ < 10)
            System.out.println(randomPick.pick());
    }

}

package hashing;

import java.util.*;

//https://leetcode.com/problems/random-pick-with-blacklist
public class Random_Pick_with_Blacklist {

    Map<Integer, Integer> blackListMap;
    Random random;
    int randomBound;

    public Random_Pick_with_Blacklist(int N, int[] blacklist) {
        int whitelistLen = N - blacklist.length;

        Set<Integer> numbersAfterWhitelistLen = new HashSet<>();
        for (int i = whitelistLen; i < N; i++) {
            numbersAfterWhitelistLen.add(i);
        }
        for (int b : blacklist) {
            numbersAfterWhitelistLen.remove(b);
        }

        blackListMap = new HashMap<>();

        Iterator<Integer> whitelistAfterWhitelistLen = numbersAfterWhitelistLen.iterator();
        for (int b : blacklist) {
            if (b < whitelistLen) {
                blackListMap.put(b, whitelistAfterWhitelistLen.next());
            }
        }

        random = new Random();
        randomBound = whitelistLen;
    }

    public int pick() {
        int number = random.nextInt(randomBound);
        return blackListMap.getOrDefault(number, number);
    }

    public static void main(String[] args) {
        Random_Pick_with_Blacklist randomPick = new Random_Pick_with_Blacklist(1, new int[0]);
        System.out.println(randomPick.pick());
        System.out.println(randomPick.pick());
    }

}

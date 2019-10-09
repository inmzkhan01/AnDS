package dp;

public class CoinChangeProblem {

    static int[] coins = {5, 1, 10};

    static class Recursive {
        static int makeChange(int c) {
            if (c == 0) return 0;
            int minCounts = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (c - coin >= 0) {
                    int newMinCount = makeChange(c - coin);
                    if (newMinCount < minCounts) {
                        minCounts = newMinCount;
                    }
                }
            }
            return minCounts + 1;
        }
    }


    static class RecursiveMemorized {
        static int makeChange(int c) {
            if (c == 0) return 0;
            int[] cache = new int[c + 1];
            return makeChange(c, cache);
        }

        static int makeChange(int c, int[] cache) {
            if (c == 0) return 0;
            if (cache[c] != 0) return cache[c];

            int minCounts = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (c - coin >= 0) {
                    int newMinCount = makeChange(c - coin, cache);
                    if (newMinCount < minCounts) {
                        minCounts = newMinCount;
                    }
                }
            }
            return cache[c] = minCounts + 1;
        }
    }


    static class BottomUpDP {

        static int makeChange(int amount) {
            int[] dp = new int[amount+1];

            for(int c=1; c<=amount; c++) {
                int minCounts = Integer.MAX_VALUE;

                for(int coin : coins) {
                    if(c - coin >= 0) {
                        int currCounts = dp[c-coin] + 1;
                        if(currCounts < minCounts) {
                            minCounts = currCounts;
                        }
                    }
                }
                dp[c] = minCounts;
            }
            return dp[amount];
        }
    }

    public static void main(String[] args) {
        System.out.println("Recursive.makeChange(21): " + Recursive.makeChange(21));
        System.out.println("RecursiveMemorized.makeChange(17): " + RecursiveMemorized.makeChange(17));
        System.out.println("BottomUpDP.makeChange(17): " + BottomUpDP.makeChange(17));
    }
}

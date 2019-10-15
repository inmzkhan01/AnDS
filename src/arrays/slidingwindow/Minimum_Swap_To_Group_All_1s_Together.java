package arrays.slidingwindow;

public class Minimum_Swap_To_Group_All_1s_Together {

    public int minSwap(int[] arr) {
        int n = arr.length;

        int noOfOnes = 0;
        for(int i=0; i<n; i++) {
            if(arr[i] == 1) {
                ++noOfOnes;
            }
        }

        int[] noOfOnesTillIndex = new int[n];

        if(arr[0] == 1) {
            noOfOnesTillIndex[0] = 1;
        }

        for(int i=1; i<n; i++) {
            noOfOnesTillIndex[i] = noOfOnesTillIndex[i-1] + arr[i];
        }

        int x = noOfOnes;

        int maxOnes = Integer.MAX_VALUE;

        int[] range = new int[2];

        for(int i = x-1; i<n; i++) {
            if(i == x-1) {
                noOfOnes = noOfOnesTillIndex[i];
            } else {
                noOfOnes = noOfOnesTillIndex[i] - noOfOnesTillIndex[i-x];
            }

            if(noOfOnes < maxOnes) {
                maxOnes = noOfOnes;
                range[0] = i-x;
                range[1] = i;
            }
        }
        return x - maxOnes;
    }
}

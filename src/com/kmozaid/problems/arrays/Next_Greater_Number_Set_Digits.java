package com.kmozaid.problems.arrays;

import java.util.Arrays;

//https://www.geeksforgeeks.org/find-next-greater-number-set-digits/
public class Next_Greater_Number_Set_Digits {

    public static void main(String[] args) {

        int x = 1999999999;

        System.out.println(x);

        int a = 218765;
        int b = 1234;
        int c = 4321;
        int d = 534976;
        int z = 230241;

        //nextGreaterNumber(a);
        //nextGreaterNumber(b);
        //nextGreaterNumber(c);
        //nextGreaterNumber(d);
        System.out.println(nextGreaterNumber(x));
    }

    static int nextGreaterNumber(int n) {

        int number = n;

        int base = 10;
        int length = (int) Math.floor(Math.log(number) / Math.log(base)) + 1;
        int[] arr = new int[length];

        int k = length;
        while (k > 0) {
            arr[--k] = number % base;
            number = number / base;
        }

        int i;

        for (i = length-1; i > 0; i--) {
            if (arr[i-1] < arr[i]) {
                break;
            }
        }

        if (i == 0) {
            return -1;
        }

        int x = arr[i-1], nextBigNumberIndex = i;

        for (int j = i + 1; j < length; j++) {
            if (arr[j] > x && arr[j] < arr[nextBigNumberIndex]) {
                nextBigNumberIndex = j;
            }
        }

        arr[i-1] = arr[nextBigNumberIndex];
        arr[nextBigNumberIndex] = x;

        Arrays.sort(arr, i, length);

        int nextGreatorNumber = 0;
        for (int z : arr) {
            if(nextGreatorNumber > Integer.MAX_VALUE / 10) {
                return -1;
            }
            nextGreatorNumber = nextGreatorNumber * 10 + z;
        }

        return nextGreatorNumber;
    }

}

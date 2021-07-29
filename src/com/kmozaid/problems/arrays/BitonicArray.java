package com.kmozaid.problems.arrays;

import java.util.Scanner;

public class BitonicArray {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for(int i=0; i<testCases; i++) {
            int[] elements = new int[sc.nextInt()];
            for(int j=0; j<elements.length; j++){
                elements[j]=sc.nextInt();
            }
            int key = sc.nextInt();
            int start=0, end=elements.length - 1;

            int mid = (start+end) / 2;

            while(mid!=0 || mid!=elements.length-1) {
                int midNumber = elements[mid];
                if(midNumber==key){
                    System.out.println(mid);
                    break;
                }
                if(key >= elements[start] && key<midNumber) {
                    mid = (int) Math.floor((start+mid) / 2.0);
                } else {
                    mid = (int) Math.ceil((end+mid) / 2.0);
                }
            }

            System.out.println(-1);
        }
    }

}

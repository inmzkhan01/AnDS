package arrays.sorting;

import java.util.Arrays;

public class Radix_Sort {

    public static void main(String[] args) {
        sortBinary();
        //sortDecimal();
    }

    static void sortDecimal() {
        //int[] A = {10,1,10220,8};
        int[] A = {9,3,1,4,5,7,7,2,2,5};
        radixSort(A, 10);
    }

    static void sortBinary() {
        int[] A = {110,100,101,001};
        radixSort(A, 2);
    }
    /**
     *
     * @param A
     * @param radix is the base of number system. radix == base
     */
    public static void radixSort(int[] A, int radix) {
        int max = Arrays.stream(A).max().orElseThrow(() -> new RuntimeException("Array can't be empty"));
        int log_base_radix_of_max = (int)Math.floor((Math.log(max) / Math.log(radix)));
        int numberOfDigitsInMax = log_base_radix_of_max + 1;
        System.out.println("Number of digits to represent max number: "+numberOfDigitsInMax);

        for(int i=0; i<numberOfDigitsInMax; i++) {
            countingSort(A, radix, i);
        }
    }

    public static void countingSort(int[] A, int radix, int radixPower) {
        System.out.println("A: "+Arrays.toString(A));

        int place = (int)Math.pow(radix, radixPower);

        int[] B = new int[A.length];

        int[] C = new int[radix];

        for(int i=0; i<A.length; i++) {
            int digitOfA = (A[i]/place)%radix;
            C[digitOfA]++;
        }

        for(int i=1; i<C.length; i++) {
            C[i] += C[i-1];
        }

        System.out.println("C: "+Arrays.toString(C));

        for(int i=A.length-1; i>=0; i--) {
            int digitOfA = (A[i]/place)%radix;
            B[C[digitOfA]-1] = A[i];
            --C[digitOfA];
        }

        System.out.println("B: "+Arrays.toString(B));
        //A is local reference, Changing this A reference won't change original array object so we need to copy every elements into A.
        //A = B;
        for (int i = 0; i < A.length; i++) {
            A[i] = B[i];
        }
    }

}
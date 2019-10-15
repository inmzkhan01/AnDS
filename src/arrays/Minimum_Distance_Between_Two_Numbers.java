package arrays;

//https://www.geeksforgeeks.org/find-the-minimum-distance-between-two-numbers/
public class Minimum_Distance_Between_Two_Numbers {

    static int minimumDistance(int[] arr, int x, int y) {

        int minDistance = Integer.MAX_VALUE;

        int first = -1;

        int i = 0;

        for(;i<arr.length; i++) {
            if(arr[i]==x || arr[i]==y) {
                first = i;
                break;
            }
        }

        if(first == -1) {
            return -1;
        }

        for(;i<arr.length;i++) {
            if(arr[i]==x || arr[i]==y) {

                if(arr[i] != arr[first] && (i-first) < minDistance) {
                    minDistance = i-first;
                    first = i;
                } else {
                    first = i;
                }
            }
        }
        return minDistance;
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 3, 5, 4, 4, 2, 3};

        System.out.println(minimumDistance(arr, 2,3));
    }
}

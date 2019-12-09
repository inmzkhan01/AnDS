package arrays;

//https://practice.geeksforgeeks.org/problems/row-with-max-1s/0
public class Row_With_Max_1s {

    public static int rowWithMax(int[][] a) {
        int i = 0;
        for (int j = a[0].length - 1; i < a.length && j >= 0;) {
            if (a[i][j] == 1) {
                j--;
            } else {
                i++;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[][] a = {
                {0, 1, 1, 1},
                {0, 0, 1, 1},
                {1, 1, 1, 1},
                {0, 0, 0, 0}
        };
        System.out.println("Row with max 1: " + rowWithMax(a));

        int[][] b = {
                {0, 0},
                {1, 1}
        };

        System.out.println("Row with max 1 " + rowWithMax(b));
    }
}

package arrays;

import java.util.Scanner;

public class DynamicConnectivity {

    public static void main(String[] args) {
        System.out.println("########Dynamic Connectivity########");
        System.out.println("Union method connects two elements");
        System.out.println("Connected method checks if two objects are elements or not.");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = -1;
        }

        boolean run = true;
        while (run) {
            System.out.println("\nChoose an option: ");
            System.out.println("\"1\" for union");
            System.out.println("\"2\" for connected");
            System.out.println("\"0\" for exit");
            System.out.print("Enter your input ?: ");

            int action = sc.nextInt();

            int x, y;
            switch (action) {
                case 1:
                    System.out.print("\nEnter space separated two elements: ");
                    x = sc.nextInt();
                    y = sc.nextInt();
                    union(x, y, arr);
                    break;
                case 2:
                    System.out.print("\nEnter space separated two elements: ");
                    x = sc.nextInt();
                    y = sc.nextInt();
                    connected(x, y, arr);
                    break;
                case 0:
                    run = false;
                    break;
                default:
                    System.out.println("\nInvalid input, please try again...");
            }
        }
    }

    // 4,3: 3,8: 6,5: 9,4: 2,1
    //CONNECTED 0,7: 8,9
    // 5,0: 7,2: 6,1: 1,0:
    //CONNECTED 0,7

    //PATH: 0,1,2,5,6,7
    //PATH: 3,4,8,9
    static void union(int x, int y, int[] arr) {
        if (arr[x] == -1 && arr[y] == -1) {
            arr[x] = y;
            arr[y] = y;
        } else if (arr[x] == -1) {
            arr[x] = x;
            int valueAtY = arr[y];
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == valueAtY) {
                    arr[i] = x;
                }
            }
        } else if (arr[y] == -1) {
            arr[y] = y;
            int valueAtX = arr[x];
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == valueAtX) {
                    arr[i] = y;
                }
            }
        } else {
            int valueAtY = arr[y];
            int valueAtX = arr[x];
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == valueAtX) {
                    arr[i] = valueAtY;
                }
            }
        }
    }

    static void connected(int x, int y, int[] arr) {
        if (arr[x] != -1 && arr[y] != -1 && arr[x] == arr[y]) {
            System.out.println(String.format("(%s,%s) are connected.", x, y));
        } else {
            System.out.println(String.format("(%s,%s) are not connected.", x, y));
        }
    }
}

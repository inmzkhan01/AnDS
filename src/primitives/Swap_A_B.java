package primitives;

public class Swap_A_B {

    public static void swap(int a, int b) {

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        /*
        a = a + b;
        b = a - b;
        a = a - b;
        */

        System.out.println("A " + a + " B " + b);
    }

    public static void main(String[] args) {
        swap(5, 7);
    }
}

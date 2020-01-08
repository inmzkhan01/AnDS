package primitives;

public class Find_Parity {

    public static int parity(long n) {
        long result = 0;
        while (n > 0) {
            result = result ^ (n & 1);
            n = n >> 1;
        }

        return (int) result;
    }

    public static int parity_by_counting_bits(long n) {
        int count = 0;
        while (n > 0) {
            count++;
            n = n & (n - 1);
        }

        return count % 2;
    }

    public static void main(String[] args) {
        System.out.println(parity(10));
    }
}


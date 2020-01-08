package arrays;

public class Maximum_Subarray_Sum__Largest_Sum_Contiguous_Subarray {

    /**
     * Quadratic
     */
    static class Naive {
        static int maxSubArraySum(int[] a) {

            int max = Integer.MIN_VALUE;
            int start = 0;
            int end = 0;

            for (int i = 1; i <= a.length; i++) {
                int sum = 0;

                for (int j = 0; j < a.length; j++) {
                    if (j < i)
                        sum += a[j];
                    else
                        sum += (a[j] - a[j - i]);

                    if (max < sum) {
                        max = sum;
                        start = j - i + 1;
                        end = j;
                    }
                }
            }

            for (int i = start; i <= end; i++) {
                System.out.print(a[i] + " ");
            }
            return max;
        }

    }

    /**
     * Linearithmic
     */
    static class Divide_and_Conquer {

        static int maxSubArraySum(int[] a) {
            return maxSubArraySum(a, 0, a.length - 1);
        }

        static int maxSubArraySum(int[] a, int lo, int hi) {
            if (lo == hi)
                return a[lo];

            int mid = lo + (hi - lo) / 2;

            return Math.max(Math.max(maxSubArraySum(a, lo, mid), maxSubArraySum(a, mid + 1, hi)), maxCrossingSum(a, lo, mid, hi));
        }

        static int maxCrossingSum(int[] a, int lo, int mid, int hi) {
            int sum = 0;
            int left_sum = Integer.MIN_VALUE;
            for (int i = mid; i >= lo; i--) {
                sum = sum + a[i];
                if (sum > left_sum)
                    left_sum = sum;
            }

            sum = 0;
            int right_sum = Integer.MIN_VALUE;
            for (int i = mid + 1; i <= hi; i++) {
                sum = sum + a[i];
                if (sum > right_sum)
                    right_sum = sum;
            }

            return left_sum + right_sum;
        }
    }

    /**
     * Linear
     */
    static class Kadane_Algorithm {

        static int maxSubArraySum(int[] a) {
            int max = Integer.MIN_VALUE, sum = 0;
            int s = 0, start = s, end = 0;

            for (int i = 0; i < a.length; i++) {
                sum += a[i];

                if (sum > max) {
                    max = sum;
                    start = s;
                    end = i;
                }


                if (sum < 0) {
                    sum = 0;
                    s = i + 1;
                }
            }

            for (int i = start; i <= end; i++) {
                System.out.print(a[i] + " ");
            }
            return max;
        }

    }

    public static void main(String[] args) {
        System.out.println("Maximum Contiguous Sum: " + Naive.maxSubArraySum(new int[]{-2, -5, 6, -2, -3, 1, 5, -6}));
        System.out.println("Maximum Contiguous Sum: " + Kadane_Algorithm.maxSubArraySum(new int[]{-2, -5, 6, -2, -3, 1, 5, -6}));
        System.out.println("Maximum Contiguous Sum: " + Divide_and_Conquer.maxSubArraySum(new int[]{-2, -5, 6, -2, -3, 1, 5, -6}));
    }
}

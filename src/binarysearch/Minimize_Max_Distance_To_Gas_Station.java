package binarysearch;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://www.lintcode.com/problem/minimize-max-distance-to-gas-station/description
public class Minimize_Max_Distance_To_Gas_Station {

    static double minmaxGasDist(int[] stations, int k) {
        int n = stations.length;
        double start = 0, end = stations[n - 1] - stations[0];
        while (end - start > 1e-6) {
            double mid = (start + end) / 2;
            int count = 0;
            for (int i = 1; i < stations.length; i++) {
                count += (int) ((stations[i] - stations[i - 1]) / mid);
            }
            if (count > k) {
                // minimum stations added is > K
                // so we need to increase the mid
                start = mid;
            } else {
                // minimum stations added is <= K
                // so we need to narrow the mid
                end = mid;
            }
        }
        return end;
    }

    public static void main(String[] args) {
        System.out.println(minmaxGasDist(new int[]{1, 5, 10}, 3));
        System.out.println(minmaxGasDist(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 9));
        System.out.println(minmaxGasDist(new int[]{3, 6, 12, 19, 33, 44, 67, 72, 89, 95}, 2));

        System.out.println(PriorityQueueSolution.minmaxGasDist(new int[]{1, 5, 10}, 3));
        System.out.println(PriorityQueueSolution.minmaxGasDist(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 9));
        System.out.println(PriorityQueueSolution.minmaxGasDist(new int[]{3, 6, 12, 19, 33, 44, 67, 72, 89, 95}, 2));
    }

    static class PriorityQueueSolution {
        public static double minmaxGasDist(int[] stations, int K) {
            int N = stations.length;

            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return (double) b[0] / b[1] > ((double) a[0] / a[1]) ? 1 : -11;
                }

            });

            for (int i = 0; i < N - 1; ++i)
                pq.add(new int[]{stations[i + 1] - stations[i], 1});

            for (int k = 0; k < K; ++k) {
                int[] node = pq.poll();
                node[1]++;
                pq.add(node);
            }

            int[] node = pq.poll();
            return (double) node[0] / node[1];
        }
    }
}

package com.kmozaid.problems.trees.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;

public class FIND_THE_CLOSEST_ENTRIES_IN_THREE_SORTED_ARRAYS {

    private static class ArrayData implements Comparable<ArrayData> {
        private int val;
        private int idx;

        ArrayData(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public int compareTo(ArrayData o) {
            int result = Integer.compare(val, o.val);

            if (result == 0) {
                result = Integer.compare(idx, o.idx);
            }
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ArrayData arrayData = (ArrayData) o;
            return val == arrayData.val &&
                    idx == arrayData.idx;
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, idx);
        }
    }

    public static int findMinDistanceSortedArrays(List<List<Integer>> arrays) {
        int minDist = Integer.MAX_VALUE;

        List<Integer> heads = new ArrayList<>(arrays.size());
        NavigableSet<ArrayData> entries = new TreeSet<>();

        for (int i = 0; i < arrays.size(); i++) {
            List<Integer> arr = arrays.get(i);
            entries.add(new ArrayData(arr.get(0), i));
            heads.add(1);
        }

        while (true) {
            minDist = Math.min(minDist, entries.last().val - entries.first().val);
            int nextMinIdx = entries.first().idx;
            if (heads.get(nextMinIdx) >= arrays.get(nextMinIdx).size()) {
                entries.stream().forEach(d -> System.out.print(d.val + " "));
                System.out.print("\n");
                return minDist;
            }
            entries.pollFirst();
            entries.add(new ArrayData(arrays.get(nextMinIdx).get(heads.get(nextMinIdx)), nextMinIdx));
            heads.set(nextMinIdx, heads.get(nextMinIdx) + 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> arrays = new ArrayList<>();
        arrays.add(Arrays.asList(5, 10, 15));
        arrays.add(Arrays.asList(3, 6, 9, 12, 15));
        arrays.add(Arrays.asList(8, 16, 24));

        System.out.println("Minimum interval: " + findMinDistanceSortedArrays(arrays));
    }
}

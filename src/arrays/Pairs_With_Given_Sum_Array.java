package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Pairs_With_Given_Sum_Array {

    static class IndexPair {

        int i, j;

        IndexPair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return new StringBuilder("(").append(i).append(",").append(j).append(")").toString();
        }
    }


    public static List<IndexPair> indexPairs(int[] arr, int sum) {
        HashMap<Integer, Integer> diffMap = new HashMap<>();
        List<IndexPair> indexPairList = new ArrayList<>();
        for(int i=0; i<arr.length; i++) {
            if(diffMap.containsKey(arr[i])) {
                indexPairList.add(new IndexPair(diffMap.get(arr[i]), i));
            } else {
              diffMap.put(sum-arr[i], i);
            }
        }

        return indexPairList;
    }

    public static void main(String[] args) {
        int[] arr = {8, 7, 2, 5, 3, 1};
        int sum = 10;

        List<IndexPair> indexPairs = indexPairs(arr, sum);

        System.out.println("Indexs:" + indexPairs);

        System.out.println("Values:" + indexPairs.stream().map(ip -> new IndexPair(arr[ip.i], arr[ip.j])).collect(Collectors.toList()));

    }
}

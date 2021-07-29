package com.mozaid.dsa.priorityqueue;


public class IndexMaxPQ<Key extends Comparable<Key>> {
    private int n;           // number of elements on PQ
    private int[] pq;        // binary heap using 1-based indexing
    private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys;      // keys[i] = priority of i

    public IndexMaxPQ(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException();
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public boolean contains(int i) {
        return qp[i] != -1;
    }

    public void insert(int i, Key key) {
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    public int maxIndex() {
        return pq[1];
    }

    public Key maxKey() {
        return keys[pq[1]];
    }

    public int delMax() {
        int max = pq[1];
        exch(1, n--);
        sink(1);

        assert pq[n + 1] == max;
        qp[max] = -1;        // delete
        keys[max] = null;    // to help with garbage collection
        pq[n + 1] = -1;        // not needed
        return max;
    }

    public Key keyOf(int i) {
        return keys[i];
    }

    public void changeKey(int i, Key key) {
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }


    public void increaseKey(int i, Key key) {
        keys[i] = key;
        swim(qp[i]);
    }

    public void decreaseKey(int i, Key key) {
        keys[i] = key;
        sink(qp[i]);
    }

    public void delete(int i) {
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }


    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

}


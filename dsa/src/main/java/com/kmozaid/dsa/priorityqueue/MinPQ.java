package com.kmozaid.dsa.priorityqueue;

public class MinPQ<T extends Comparable<T>> {

    private T[] pq;
    private int n;

    public MinPQ(int initialCapacity) {
        pq = (T[]) new Comparable[initialCapacity + 1];
    }

    public MinPQ(T[] keys) {
        n = keys.length;
        pq = (T[]) new Object[keys.length + 1];
        for (int i = 0; i < n; i++)
            pq[i+1] = keys[i];
        for (int k = n/2; k >= 1; k--)
            sink(k);
    }

    public void insert(T item) {
        if (n == pq.length - 1)
            resize(2 * pq.length);

        pq[++n] = item;
        swim(n);
    }

    public T delMin() {
        T min = pq[1];
        exch(1, n);
        pq[n] = null;
        n--;
        sink(1);

        if ((n > 0) && (n == (pq.length - 1) / 4))
            resize(pq.length / 2);

        return min;
    }

    public T min() {
        return pq[1];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;

            if (j < n && greater(j, j + 1)) j++;

            if (greater(j, k)) break;

            exch(j, k);

            k = j;
        }
    }

    private boolean greater(int v, int w) {
        return pq[v].compareTo(pq[w]) > 0;
    }

    private void exch(int v, int w) {
        T temp = pq[v];
        pq[v] = pq[w];
        pq[w] = temp;
    }

    private void resize(int capacity) {
        assert capacity > n;
        T[] temp = (T[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }
}
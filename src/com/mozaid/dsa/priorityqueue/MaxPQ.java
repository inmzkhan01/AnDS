package com.mozaid.dsa.priorityqueue;

public class MaxPQ<T extends Comparable<T>> {

    private T[] pq;
    private int n;

    public MaxPQ(int initCapacity) {
        pq = (T[]) new Comparable[initCapacity + 1];
    }

    public MaxPQ(T[] keys) {
        n = keys.length;
        pq = (T[]) new Object[keys.length + 1];
        for (int i = 0; i < n; i++)
            pq[i + 1] = keys[i];
        for (int k = n / 2; k >= 1; k--)
            sink(k);
    }

    public void insert(T item) {
        /*if (n == pq.length - 1)
            resize(2 * pq.length);*/

        pq[++n] = item;
        swim(n);
    }

    public T delMax() {
        T max = pq[1];
        exch(1, n);
        pq[n] = null;
        n--;
        sink(1);

        //if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);

        return max;
    }

    public T max() {
        return pq[1];
    }

    public int size() {
        return n;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;

            if (j < n && less(j, j + 1)) j++;

            if (less(j, k)) break;

            exch(j, k);

            k = j;
        }
    }

    private boolean less(int v, int w) {
        return pq[v].compareTo(pq[w]) < 0;
    }

    private void exch(int v, int w) {
        T temp = pq[v];
        pq[v] = pq[w];
        pq[w] = temp;
    }

    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public static void main(String[] args) {
        MaxPQ<Character> pq = new MaxPQ<>(10);
        pq.insert('K');
        pq.insert('M');
        pq.insert('A');
        System.out.println(pq.delMax());
        pq.insert('I');
        pq.insert('Z');
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
        System.out.println(pq.size());
    }


}

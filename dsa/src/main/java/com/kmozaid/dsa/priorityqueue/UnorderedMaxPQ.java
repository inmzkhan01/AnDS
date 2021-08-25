package com.kmozaid.dsa.priorityqueue;

public class UnorderedMaxPQ<T extends Comparable<T>> {

    private T[] pq;
    private int N;

    public UnorderedMaxPQ(int N) {
        pq = (T[]) new Comparable[N];
    }

    public void insert(T item) {
        pq[N++] = item;
    }

    public T delMax() {
        int max = 0;
        for (int i = 1; i < N; i++) {
            if (less(pq[max], (pq[i])))
                max = i;
        }

        exch(max, N - 1);

        T item = pq[--N];
        pq[N] = null;
        return item;
    }

    public int size() {
        return N;
    }

    private boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private void exch(int v, int w) {
        T temp = pq[v];
        pq[v] = pq[w];
        pq[w] = temp;
    }

    public static void main(String[] args) {
        UnorderedMaxPQ<Character> pq = new UnorderedMaxPQ<>(10);
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

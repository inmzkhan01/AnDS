package algorithms.stackqueue;

import java.util.Arrays;
import java.util.Iterator;

public class ResizingArrayStack<T> implements Iterable<T> {

    private T[] a;
    private int n;

    public ResizingArrayStack() {
        a = (T[]) new Object[2];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void push(T t) {
        if (n == a.length) {
            resize(2 * a.length);
        }
        a[n++] = t;
    }

    public T pop() {
        T t = a[--n];
        a[n] = null;
        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }
        return t;
    }

    private void resize(int size) {
        T[] ts = (T[]) new Object[size];
        for (int i = 0; i < n; i++) {
            ts[i] = a[i];
        }
        a = ts;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {

        int i = n;

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public T next() {
            return a[--i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported");
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(a);
    }
}
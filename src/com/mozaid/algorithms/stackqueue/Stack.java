package com.mozaid.algorithms.stackqueue;

public interface Stack<T> extends Iterable<T> {

    void push(T t);

    T pop();

    T peek();

    int size();

    boolean isEmpty();

}

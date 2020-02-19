package com.mozaid.algorithms.stackqueue;

public interface Stack<T> extends Iterable<T> {

    void push(T t);

    T pop();

    int size();

    boolean isEmpty();

}

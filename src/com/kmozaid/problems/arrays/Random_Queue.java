package com.kmozaid.problems.arrays;

import java.util.Arrays;
import java.util.Random;

public class Random_Queue {

    private int[] elements;
    int size;
    int index;

    public Random_Queue() {
        elements = new int[10];
        size = 0;
        index = 0;
    }


    public void enque(int number) {
        elements[index] = number;
        index++;
        size++;
    }

    public int deque() {
        int randomIndex = new Random().nextInt(size);
        int number = elements[randomIndex];
        elements[randomIndex] = elements[size-1];
        elements[size-1] = 0;
        size--;
        index--;
        return number;
    }


    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    public static void main(String[] args) {
        Random_Queue randomQueue = new Random_Queue();
        randomQueue.enque(13);
        randomQueue.enque(16);
        randomQueue.enque(21);
        randomQueue.enque(28);
        randomQueue.enque(19);
        randomQueue.enque(32);
        System.out.println(randomQueue);
        System.out.println("Size: " +randomQueue.size());
        System.out.println(randomQueue.deque());
        System.out.println(randomQueue.deque());

        System.out.println(randomQueue);
        System.out.println("Size: " +randomQueue.size());

        randomQueue.enque(30);
        randomQueue.enque(41);
        System.out.println(randomQueue);
        System.out.println("Size: " +randomQueue.size());
    }

}

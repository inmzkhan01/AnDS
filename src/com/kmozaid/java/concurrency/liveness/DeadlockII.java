package com.kmozaid.java.concurrency.liveness;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class DeadlockII {

    public static class Account {
        private int balance;
        private int id;
        private static int globalId;

        public Account(int balance) {
            this.balance = balance;
            this.id = ++globalId;
        }

        /*
        //This can create deadlock.
        public void transfer(int amount, Account to) {
            System.out.println(Thread.currentThread().getName() + "request \"this\" lock");
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + "request \"to\" lock.");
                synchronized (to) {
                    if (amount > this.balance)
                        throw new IllegalStateException("Not enough fund");
                    this.balance -= amount;
                    to.balance += amount;
                }
            }
        }*/

        // The canonical way to avoid deadlock is to have a global ordering on locks and acquire them in that order
        // Does not matter if lockl equals lock2: since Java locks are reentrant , we will re-acquire lock2.
        public void transfer(int amount, Account to) {
            final Account lock1 = id < to.id ? this : to;
            final Account lock2 = id < to.id ? to : this;
            System.out.println(Thread.currentThread().getName() + "request \"lock1\" lock");
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + "request \"lock2\" lock.");
                synchronized (lock2) {
                    if (amount > balance)
                        throw new IllegalStateException("Not enough fund");
                    balance -= amount;
                    to.balance += amount;
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Account account1 = new Account(100000);
        Account account2 = new Account(100000);

        ExecutorService executor = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 500).forEach(i -> {
            executor.submit(() -> account1.transfer(1000, account2));
            executor.submit(() -> account2.transfer(1000, account1));
        });

        executor.awaitTermination(2, TimeUnit.SECONDS);
        executor.shutdown();
    }
}

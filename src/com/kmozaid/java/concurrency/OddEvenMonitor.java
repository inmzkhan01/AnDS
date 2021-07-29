package com.kmozaid.java;

public class OddEvenMonitor {

    private static final boolean ODD = true;
    private static final boolean EVEN = false;
    private static boolean turn = ODD;

    public synchronized void waitTurn(boolean oldTurn) {
        while (turn != oldTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
    }

    public synchronized void toggleTurn() {
        turn = !turn;
        notify();
    }


    public static class OddThread extends Thread {

        private final OddEvenMonitor monitor;

        public OddThread(OddEvenMonitor m) {
            monitor = m;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 100; i += 2) {
                if (turn != ODD)
                    monitor.waitTurn(ODD);
                System.out.print(i + " ");
                monitor.toggleTurn();
            }
        }
    }

    public static class EvenThread extends Thread {

        private final OddEvenMonitor monitor;

        public EvenThread(OddEvenMonitor m) {
            monitor = m;
        }

        @Override
        public void run() {
            for (int i = 2; i <= 100; i += 2) {
                if (turn != EVEN)
                    monitor.waitTurn(EVEN);
                System.out.print(i + " ");
                monitor.toggleTurn();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        OddEvenMonitor monitor = new OddEvenMonitor();
        Thread oddThread = new OddThread(monitor);
        Thread evenThread = new EvenThread(monitor);
        oddThread.start();
        evenThread.start();
    }
}

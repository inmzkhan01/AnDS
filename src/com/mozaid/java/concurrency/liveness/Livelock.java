package com.mozaid.java.concurrency.liveness;

public class Livelock {

    static class Spoon {
        Diner owner;

        Spoon(Diner diner) {
            owner = diner;
        }

        public synchronized void setOwner(Diner diner) {
            owner = diner;
        }
    }

    static class Diner {
        String name;
        boolean isHungry;
    }


}

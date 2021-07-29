package com.mozaid.designpatterns.singleton.approach2;

/**
 * public member function.
 */
public class Elvis {
    private static final Elvis INSTANCE = new Elvis();

    private Elvis() {}

    public static Elvis getInstance() {
        return INSTANCE;
    }

    public void leaveThisBuilding() {
        // Implementation
    }
}

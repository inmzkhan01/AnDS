package com.kmozaid.designpatterns.singleton.approach1;

/**
 * public member field.
 */
public class Elvis {
    public static final Elvis INSTANCE = new Elvis();

    private Elvis() {}

    public void leaveThisBuilding() {
        // Implementation
    }
}

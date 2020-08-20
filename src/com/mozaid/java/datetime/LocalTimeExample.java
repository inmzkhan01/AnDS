package com.mozaid.java.datetime;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

public class LocalTimeExample {

    public static void main(String[] args) {
        LocalTime time1 = LocalTime.parse("12:00:59");
        LocalTime time2 = LocalTime.parse("12:01:07");

        long result = time1.until(time2, ChronoUnit.SECONDS);
        System.out.println(result);

        LinkedHashMap<LocalTime, String> map = new LinkedHashMap<LocalTime, String>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<LocalTime, String> eldest) {
                return Math.abs(eldest.getKey().until(LocalTime.now(), ChronoUnit.SECONDS)) > 2;
            }
        };

        LocalTime now = LocalTime.now();
        map.put(now, "Zaid");
        map.put(now.plus(5, ChronoUnit.SECONDS), "Cheena");
        map.put(now.plus(10, ChronoUnit.SECONDS), "Maaz");
        map.put(now.plus(20, ChronoUnit.SECONDS), "Girish");

        System.out.println(map);
    }

}

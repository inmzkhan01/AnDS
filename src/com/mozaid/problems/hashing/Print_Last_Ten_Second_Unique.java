package com.mozaid.problems.hashing;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Print_Last_Ten_Second_Unique {

    public static void print(List<String> streamData) {
        Map<String, LocalTime> map = new LinkedHashMap<String, LocalTime>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, LocalTime> eldest) {
                return Math.abs(LocalTime.now().until(eldest.getValue(), ChronoUnit.SECONDS)) > 10;
            }
        };
        for (String data : streamData) {
            String key = data.split("@")[0];
            LocalTime time = LocalTime.parse(data.split("@")[1]);
            if (!map.containsKey(key)) {
                System.out.println(key);
            } else if (Math.abs(time.until(map.get(key), ChronoUnit.SECONDS)) > 10) {
                System.out.println(key);
                map.remove(key);
            }
            map.put(key, time);
        }
        System.out.println(map);
    }

    public static void main(String[] args) {
        List<String> streamData = new ArrayList<>();
        streamData.add("Foo @09:28:59");
        streamData.add("Foo @09:26:59");
        streamData.add("Foo @09:27:59");
        streamData.add("Bar @09:30:59");
        streamData.add("Foo @09:26:59");
        streamData.add("Bar @09:26:59");
        streamData.add("Foo @09:26:59");
        print(streamData);
    }
}

package com.mozaid.problems.regex;

import com.mozaid.algorithms.stackqueue.ResizingArrayQueue;

import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {

    private static String URL_REGEX = "http://(\\+w\\.)*(\\w+)";

    // used to read the entire input. source:
    // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
    private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");


    public static Set<String> crawl(String sourceURL) {
        Set<String> visited = new HashSet<>();
        ResizingArrayQueue<String> queue = new ResizingArrayQueue<>();
        visited.add(sourceURL);
        queue.enqueue(sourceURL);

        while (!queue.isEmpty()) {
            String site = queue.dequeue();

            String content = pageContent(site);

            Pattern pattern = Pattern.compile(URL_REGEX);
            Matcher matcher = pattern.matcher(content);

            while (matcher.find()) {
                String s = matcher.group();

                if (!visited.contains(s)) {
                    visited.add(s);
                    queue.enqueue(s);
                }
            }

        }
        return visited;
    }

    private static String pageContent(String site) {
        try {
            URL url = new URL(site);
            URLConnection urlConnection = url.openConnection();
            Scanner scanner = new Scanner(urlConnection.getInputStream());

            if (!scanner.hasNextLine())
                return "";

            return scanner.useDelimiter(EVERYTHING_PATTERN).next();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Set<String> crawledSites = WebCrawler.crawl("http://www.google.com");
        System.out.println(crawledSites);
    }
}

package com.kmozaid.java.io;

import java.io.Console;

public class CustomConsole {

    public static void main(String args[]) {
        Console console = System.console();
        char[] password;
        password = console.readPassword();
        for(char ch : password) {
            System.out.format(" %c", ch);
        }
        String greeting = console.readLine("Hello %s:");
        System.out.println(greeting);
    }

}

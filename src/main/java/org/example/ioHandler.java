package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ioHandler {
    public static String getInput() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String text = "";
        try {
            text = input.readLine();
        } catch (IOException e) {
            System.out.println("- - INPUT ERROR - -");
            e.printStackTrace();
        }
        return text;
    }

    public static String getPrefixInput(String prefix) {
        output(prefix, false);
        return  getInput();
    }

    public static void output(String output) {
        System.out.println(output);
    }

    public static void output(String output, boolean nextLine) {
        if (nextLine) {
            System.out.println(output);
        }
        else {
            System.out.printf(output);
        }
    }
}

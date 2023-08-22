package org.example;

import org.example.database.Logging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class IOHandler {
    public static String getInput(String username) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String text = "";
        try {
            text = input.readLine();
        } catch (IOException e) {
            System.out.println("- - INPUT ERROR - -");
            e.printStackTrace();
        }
        Logging.LogInput(username, text, LocalDateTime.now());
        return text;
    }

    public static String getPrefixInput(String username, String prefix) {
        output("SYSTEM", prefix);
        return  getInput(username);
    }

    public static void output(String botName, String output) {
        System.out.printf(output);
        Logging.LogOutput(botName, output, LocalDateTime.now());
    }

}
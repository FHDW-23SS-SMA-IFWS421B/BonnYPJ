package org.example;


import org.example.database.Logging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class IOHandler {
    public static String getInput(String username, String botName) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String text = "";
        try {
            text = input.readLine();
        } catch (IOException e) {
            output(username, "SYSTEM", "Es gab einen Fehler bei der Eingabe. Versuche es erneut.");
        }
        Logging.LogInput(username, text, LocalDateTime.now(), botName);
        return text;
    }

    public static String getPrefixInput(String username, String prefix, String botName) {
        output(username, "SYSTEM", prefix);
        return  getInput(username, botName);
    }

    public static void output(String username, String botName, String output) {
        System.out.printf(output);
        Logging.LogOutput(username, output, LocalDateTime.now(), botName);
    }

}

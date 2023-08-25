package org.example;


import org.example.database.Logging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class IOHandler {
    public static String getInput(String username) throws SQLException {
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

    public static String getPrefixInput(String username, String prefix) throws SQLException {
        output(username, "SYSTEM", prefix);
        return  getInput(username);
    }

    public static void output(String username, String botName, String output) throws SQLException {
        System.out.printf(output);
        Logging.LogOutput(username, output, LocalDateTime.now(), botName);
    }

}

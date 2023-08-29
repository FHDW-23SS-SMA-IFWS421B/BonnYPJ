package org.example.database;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Logging {
    public static void LogInput(String user, String input, LocalDateTime time) {
        DBHandler.writeLogs(user, String.valueOf(time), input, "");
    }

    public static void LogOutput(String user, String output, LocalDateTime time, String bot) {
        DBHandler.writeLogs(user, String.valueOf(time), output, bot);

    }
}

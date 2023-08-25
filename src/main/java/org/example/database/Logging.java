package org.example.database;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Logging {
    public static void LogInput(String user, String input, LocalDateTime time) throws SQLException {
        DBHandler.writeUserLogs(user, String.valueOf(time), input);
    }

    public static void LogOutput(String bot, String output, LocalDateTime time) throws SQLException {
        DBHandler.writeBotLogs(bot, String.valueOf(time), output);

    }
}

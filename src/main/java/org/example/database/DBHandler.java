package org.example.database;
import java.sql.*;

import java.util.Arrays;
import java.util.HashMap;

public class DBHandler{


    public static HashMap readLogs(String username) throws SQLException {
        HashMap<Integer, String[]> hashMap = new HashMap<>();
        String target = "*";
        Integer offset;
        String tablename = "logs";
        String value = "username";
        for (offset = 0; offset<100; offset++){
            Integer key = offset + 1;
            String condition = username + "' order by timestamp desc limit 1 OFFSET " + offset;
            hashMap.put(key, DBImplementation.readDb(target, tablename, value, condition));
        }
        return hashMap;
    }

    public static void writeLogs(String username, String timestamp, String message, String bot) throws SQLException {

        String tablename = "logs";
        String columnnames = "username, timestamp, message, bot";
        String values = "'" + username + "', '" + timestamp + "', '" + message + "', '" + bot + "'";
        DBImplementation.writeDb(tablename, columnnames, values);
    }

    public static String[] getUserList() throws SQLException {
        String target = "username";
        String tablename = "user_credentials";
        String value = "'1'";
        String condition = "1'";
        return DBImplementation.readDb(target, tablename, value, condition);

    }

    public static String getUserPassword(String username) throws SQLException {
        String target = "password";
        String tablename = "user_credentials";
        String value = "username";
        String condition = username + "'";
        String[] userPassword = DBImplementation.readDb(target, tablename, value, condition);
        return String.join(" ", userPassword);
    }

    public static void deactivateBot(String botName) throws SQLException {
        String tablename = "botList";
        String target = "status";
        String value = "False";
        String condition = "bots";
        String criteria = botName;
        DBImplementation.updateDb(tablename, target, value, condition, criteria);
    }

    public static void activateBot(String botName) throws SQLException {
        String tablename = "botList";
        String target = "status";
        String value = "True";
        String condition = "bots";
        String criteria = botName;
        DBImplementation.updateDb(tablename, target, value, condition, criteria);
    }

    public static String[] botList() throws SQLException {
        String target = "bots";
        String tablename = "botList";
        String value = "'1'";
        String condition = "1'";
        String [] allMessages = DBImplementation.readDb(target, tablename, value, condition);
        return allMessages;
    }

    public static HashMap botStatusList() throws SQLException {
        HashMap<String, String> botStatus = new HashMap<>();
        String[] bot = botList();
        String target = "status";
        String tablename = "botList";
        String value = "bots";

        for (int i = 0; i < bot.length; i++) {
            String botStatusList = DBImplementation.readDb(target, tablename, value, bot[1]).toString();
            botStatus.put(bot[i], botStatusList);
        }
        return botStatus;
    }

    public static boolean botStatus(String botName) throws SQLException {
        String target = "status";
        String tablename = "botList";
        String value = "bots";
        String condition =botName + "'" ;
        String botStatus = String.join(" ", Arrays.toString(DBImplementation.readDb(target, tablename, value, condition)));
        return botStatus.equals("[True]");
    }
}

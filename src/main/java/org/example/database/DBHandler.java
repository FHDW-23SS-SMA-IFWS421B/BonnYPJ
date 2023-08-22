package org.example.database;
import java.sql.*;

import java.util.Arrays;
import java.util.HashMap;

public class DBHandler{

    public DBHandler(){
    }

    DBImplementation instanzDbImplementation = new DBImplementation();

    public Object[] readLogs(String username) throws SQLException {

        String query = "select message from logs where username = '" + username + "' order by timestamp desc limit 100";
        String columnName = "message";
        return instanzDbImplementation.readDb(query, columnName);
    }

    public void writeLogs(String username, Integer timestamp, String message, String bot) throws SQLException {

        String query = "INSERT INTO logs (username, timestamp, message, bot) VALUES ('" + username + "', '" + timestamp + "', '" + message + "', '" + bot + "')";
        instanzDbImplementation.writeDb(query);
    }

    public String[] getUserList() throws SQLException {
        String query = "select username from user_credentials";
        String columnName = "username";
        return (String[]) instanzDbImplementation.readDb(query, columnName);

    }

    public String getUserPassword(String username) throws SQLException {
        String query = "select password from user_credentials where username =" + username;
        String columnName = "username";
        String[] userPassword = (String[]) instanzDbImplementation.readDb(query, columnName);
        return String.join(" ", userPassword);
    }

    public void deactivateBot(String botName) throws SQLException {
        String query = "UPDATE botList SET status = False WHERE bots =" + botName;
        instanzDbImplementation.writeDb(query);
    }

    public void activateBot(String botName) throws SQLException {
        String query = "UPDATE botList SET status = True WHERE bots =" + botName;
        instanzDbImplementation.writeDb(query);
    }

    public String[] botList() throws SQLException {
        return instanzDbImplementation.listDbs();
    }

    public HashMap botStatusList() throws SQLException {
        HashMap<String, String> botStatus = new HashMap<>();
        String[] bot = botList();
        String query = "SELECT status from botList";
        String columnName = "status";
        String[] botStatusList = (String[]) instanzDbImplementation.readDb(query, columnName);


        for (int i = 0; i < bot.length; i++) {
            botStatus.put(bot[i], botStatusList[i]);
        }
        return botStatus;
    }

    public boolean botStatus(String botName) throws SQLException {
        String query = "Select status from botList where bots = '" + botName + "'";
        String columnName = "bots";
        String botStatus = String.join(" ", Arrays.toString(instanzDbImplementation.readDb(query, columnName)));
        return botStatus.equals("[true]");
    }
}

package org.example.ineterfaces;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface DBHandling {
    /* Interface between database and the program. */
    public Object[] readLogs(String username) throws SQLException;
    public void writeLogs(String username, Integer timestamp, String message, String bot) throws SQLException;
    public String[] getUserList() throws SQLException;
    public String getUserPassword(String username) throws SQLException;
    public void deactivateBot(String botName) throws SQLException;
    public void activateBot(String botName) throws SQLException;
    public String[] botList() throws SQLException;
    public HashMap botStatusList() throws SQLException;
    public boolean botStatus(String botName) throws SQLException;


}

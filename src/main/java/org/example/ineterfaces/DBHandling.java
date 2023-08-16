package org.example.ineterfaces;

import java.util.HashMap;

public interface DBHandling {
    /* Interface between database and the program. */
    public String readLogs(String username, int logLength);
    public void writeLogs(String username, Integer timestamp, String message, String bot);
    public String[] getUserList();
    public String getUserPassword(String user);
    public void deactivateBot(String botName);
    public void activateBot(String botName);
    public String[] botList();
    public HashMap botStatusList();
    public boolean botStatus(String botName);


}

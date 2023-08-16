package org.example.Interfaces;

import java.util.HashMap;

public interface DBHandling {
    /* Interface between database and the program. */
    public String readLogs(String username, int logLength);
    public void writeLogs(String username, Integer timestamp, String message, String bot);
    public String[] getUserList();
    public String getUserPassword(String user);
    public void deactivateBot(String botname);
    public void activateBot(String botname);
    public String[] botList();
    public HashMap botStatusList();
    public boolean botStatus(String botname);


}

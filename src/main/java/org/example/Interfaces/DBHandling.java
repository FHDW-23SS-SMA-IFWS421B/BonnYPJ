package org.example.Interfaces;

import java.util.HashMap;

public interface DBHandling {
    /* Interface between Database and Programm. */
    public String readLogs(String username, int logLength);
    public void writeLogs(String username, Integer timestamp, String message, String bot);
    public String[] getUserlist();
    public String getUserPasswort(String user);
    public void deactiveBot(String botname);
    public void activateBot(String botname);
    public String[] botList();
    public HashMap botStatusList();
    public boolean botStatus(String botname);


}

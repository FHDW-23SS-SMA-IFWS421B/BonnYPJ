package org.example.database;

import org.example.ineterfaces.DBHandling;

import java.util.HashMap;

public class DBHandler implements DBHandling {
    @Override
    public String readLogs(String username, int logLength) {
        return null;
    }

    @Override
    public void writeLogs(String username, Integer timestamp, String message, String bot) {

    }

    @Override
    public String[] getUserList() {
        return new String[0];
    }

    @Override
    public String getUserPassword(String user) {
        return null;
    }

    @Override
    public void deactivateBot(String botName) {

    }

    @Override
    public void activateBot(String botName) {

    }

    @Override
    public String[] botList() {
        return new String[0];
    }

    @Override
    public HashMap botStatusList() {
        return null;
    }

    @Override
    public boolean botStatus(String botName) {
        return false;
    }
}

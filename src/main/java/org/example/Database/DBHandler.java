package org.example.Database;

import Interface.DBHandling;

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
    public String[] getUserlist() {
        return new String[0];
    }

    @Override
    public String getUserPasswort(String user) {
        return null;
    }

    @Override
    public void deactiveBot(String botname) {

    }

    @Override
    public void activateBot(String botname) {

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
    public boolean botStatus(String botname) {
        return false;
    }
}

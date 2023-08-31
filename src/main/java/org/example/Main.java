package org.example;

import org.example.bot.bots.Persa.*;
import org.example.database.DBBuilding;
import org.example.database.DBHandler;
import org.example.database.DBImplementation;

public class Main {
    public static void main(String[] args) {
        DBImplementation.initializeConnection();
        DBBuilding dbBuilding = new DBBuilding();
        Session session = new Session();
    }
}
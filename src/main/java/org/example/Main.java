package org.example;

import org.example.bot.BotCaller;
import org.example.database.DBBuilding;
import org.example.database.DBImplementation;
import org.example.exceptions.IOException;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        DBImplementation.initializeConnection();
        DBBuilding dbBuilding = new DBBuilding();
        Session session = new Session();

    }
}
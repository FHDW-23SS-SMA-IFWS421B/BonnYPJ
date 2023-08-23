package org.example;

import org.example.database.DBBuilding;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        DBBuilding dbBuilding = new DBBuilding();
        Session session = new Session();

    }
}
package sma.bonn;

import sma.bonn.database.DBBuilding;
import sma.bonn.database.DBImplementation;

public class Main {
    public static void main(String[] args) {
        DBImplementation.initializeConnection();
        DBBuilding dbBuilding = new DBBuilding();
        Session session = new Session();
    }
}
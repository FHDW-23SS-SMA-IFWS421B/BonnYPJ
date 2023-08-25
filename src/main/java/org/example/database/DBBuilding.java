package org.example.database;


import java.sql.SQLException;

public class DBBuilding {

    public DBBuilding() throws SQLException {
        buildTableLogs();
        buildTableUserCredentials();
        buildTableBotList();
        insertUserCredentials();
        insertBots();
    }

    public static void buildTableLogs(){
        String tablenameLogs = "logs";
        String valuesLogs = "username VARCHAR(50), timestamp VARCHAR(50), message VARCHAR(1000), bot VARCHAR(50)";
        DBImplementation.createDb(tablenameLogs, valuesLogs);
    }

    public static void buildTableUserCredentials(){
        String tablenameUserCredentials = "user_credentials";
        String valuesUserCredentials = "id INT AUTO_INCREMENT, username VARCHAR(50), password VARCHAR(50)";
        DBImplementation.createDb(tablenameUserCredentials, valuesUserCredentials);
    }

    public static void buildTableBotList(){
        String tablenameBots = "botList";
        String valuesBots = "bots VARCHAR(50), status VARCHAR(50)";
        DBImplementation.createDb(tablenameBots, valuesBots);
    }

    public static void insertUserCredentials() throws SQLException {
        String tablename = "user_credentials";
        String columnnames = "username, password";

        String[][] users = {
                {"admin", "admin"},
                {"amendt", "effzeh"},
                {"hoeltje", "wolf"},
                {"abdellaoui", "freiburg"},
                {"user", "password"},
                {"fueller", "fhdw"}
        };

        String target = "username";

        for (String[] user : users) {
            String condition = user[0] + "'";
            String values = "'" + user[0] + "', '" + user[1] + "'";

            if (DBImplementation.readDb(target, tablename, target, condition).length == 0) {
                DBImplementation.writeDb(tablename, columnnames, values);
            }
        }
    }

    public static void insertBots() throws SQLException {
        String tablename = "botList";
        String columnnames = "bots, status";

        String[][] bots = {
                {"Weather", "True"},
                {"Translator", "True"},
                {"Wiki", "True"}
        };

        String target = "bots";

        for (String[] bot : bots) {
            String condition = bot[0] + "'";
            String values = "'" + bot[0] + "', '" + bot[1] + "'";

            if (DBImplementation.readDb(target, tablename, target, condition).length == 0) {
                DBImplementation.writeDb(tablename, columnnames, values);
            }
        }
    }
}

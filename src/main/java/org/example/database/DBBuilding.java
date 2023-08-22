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
        String valuesBots = "(bots VARCHAR(50), status VARCHAR(50)";
        DBImplementation.createDb(tablenameBots, valuesBots);
    }

    public static void insertUserCredentials() throws SQLException {
        String tablename = "user_credentials";
        String columnnames = "username, password";
        String valuesAdmin = "'admin', 'admin'";
        String valuesAmendt = "'amendt', 'effzeh'";
        String valuesHoeltje = "'hoeltje', 'woelf'";
        String valuesAbdellaoui = "'abdellaoui', 'arminia'";
        String valuesFueller = "'fueller', '1234'";
        String target = "username";
        String conditionAdmin = "admin";
        String conditionAmendt = "amendt";
        String conditionHoeltje = "hoeltje";
        String coniditonAbdellaoui = "abdellaoui";
        String conditionFueller = "fueller";
        if (DBImplementation.readDb(target, tablename, target, conditionAdmin).length != 0) {
            DBImplementation.writeDb(tablename, columnnames, valuesAdmin);
        }
        if (DBImplementation.readDb(target, tablename, target, conditionAmendt).length != 0) {
            DBImplementation.writeDb(tablename, columnnames, valuesAmendt);
        }
        if (DBImplementation.readDb(target, tablename, target, conditionHoeltje).length != 0){
            DBImplementation.writeDb(tablename, columnnames, valuesHoeltje);
        }
        if (DBImplementation.readDb(target, tablename, target, coniditonAbdellaoui).length != 0){
            DBImplementation.writeDb(tablename, columnnames, valuesAbdellaoui);
        }
        if (DBImplementation.readDb(target, tablename, target, conditionFueller).length != 0){
            DBImplementation.writeDb(tablename, columnnames, valuesFueller);
        }
    }

    public static void insertBots() throws SQLException {
        String tablename = "botList";
        String columnnames = "bots, status";
        String valuesWeatherBot = "Weather, True";
        String valuesTranslatorBot = "Translator, True";
        String valuesWikiBot = "Wiki, True";
        String target = "bots";
        String conditionWeather = "Weather";
        String conditionTranslator = "Translator";
        String conditionWiki = "Wiki";
        if (DBImplementation.readDb(target, tablename, target, conditionWeather).length != 0) {
            DBImplementation.writeDb(tablename, columnnames, valuesWeatherBot);
        }
        if (DBImplementation.readDb(target, tablename, target, conditionTranslator).length != 0) {
            DBImplementation.writeDb(tablename, columnnames, valuesTranslatorBot);
        }
        if (DBImplementation.readDb(target, tablename, target, conditionWiki).length != 0){
            DBImplementation.writeDb(tablename, columnnames, valuesWikiBot);
        }
    }
}

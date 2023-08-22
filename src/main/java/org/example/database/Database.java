package org.example.database;//package Database;
//import java.sql.*;
//import java.util.Arrays;
//import java.util.Collections;
//
//public class Database {
//
//
//    public void createDatabase (){
//
//        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/database/Database.db");
//            Statement stmt = conn.createStatement();
//        ) {
//            String createLogsTableQuery = "CREATE TABLE IF NOT EXISTS logs ("
//                    + "username VARCHAR(50), "
//                    + "timenig INT, "
//                    + "message VARCHAR(1000), "
//                    + "bot VARCHAR(50))";
//            String createUserTableQuery = "CREATE TABLE IF NOT EXISTS user_credentials (id INT AUTO_INCREMENT, "
//                    + "username VARCHAR(50), password VARCHAR(50))";
//            String createBotTableQuery = "CREATE TABLE IF NOT EXISTS botList (bots VARCHAR(50), status VARCHAR(50))";
//            stmt.execute(createLogsTableQuery);
//            stmt.execute(createUserTableQuery);
//            stmt.execute(createBotTableQuery);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void messageIntoDatabase(String username, Integer timenig, String message, String bot) throws SQLException {
//        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/database/Database.db");
//             Statement stmt = conn.createStatement();
//        ) {
//            String query = "INSERT INTO logs (UserName, timenig, message, bot) VALUES ('" + username + "', '" + timenig + "', '" + message + "', '" + bot + "')";
//            stmt.executeUpdate(query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//    }
//
//    public String getMessages(String username) {
//        String[] allMessages = new String[]{};
//        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/database/Database.db");
//             Statement stmt = conn.createStatement();
//        ) {
//            String query = "select * from logs where username = '" + username + "' order by timenig desc limit 100";
//            ResultSet resultSet = stmt.executeQuery(query);
//
//            while (resultSet.next()) {
//                String message = resultSet.getString("message");
//                allMessages = Arrays.copyOf(allMessages, allMessages.length + 1);
//                allMessages[allMessages.length - 1] = message;
//                return message;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        Collections.reverse(Arrays.asList(allMessages));
//        return "allMessages";
//        }
//    }
//

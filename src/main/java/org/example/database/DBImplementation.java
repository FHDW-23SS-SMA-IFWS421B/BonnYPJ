package org.example.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBImplementation{
    private static Connection conn;
    static String url = "jdbc:sqlite:src/main/java/org/example/database/Database.db";

    public static void initializeConnection(){
        try{
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    protected static void createDb(String tablename, String values) {
        try (Statement stmt = conn.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS " + tablename + "(" + values + ")";
            stmt.execute(query);

        }catch (SQLException e) {
            e.printStackTrace();

        }
    }

    protected static void writeDb(String tablename, String columnnames, String values) {
        try (Statement stmt = conn.createStatement()) {
            String query = "INSERT INTO " + tablename + " (" + columnnames + ")" + "VALUES (" + values + ")";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected static void updateDb(String tablename, String target, String value, String condition, String criteria) {
        try (Statement stmt = conn.createStatement()) {
            String query = "UPDATE " + tablename + " SET " + target + " = " + value + " WHERE " + condition + " = " + criteria;
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected static String[] readDb(String target, String tablename, String value, String condition) {
        try (Statement stmt = conn.createStatement()) {
            String query = "SELECT " + target + " FROM " + tablename + " WHERE " + value + " = '" + condition;
            List<String> allMessages = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery(query);
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String element = resultSet.getString(i);
                    allMessages.add(element);
                }

            }
            return allMessages.toArray(new String[0]);

        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}


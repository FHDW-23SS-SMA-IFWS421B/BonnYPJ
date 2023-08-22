package org.example.database;

import org.example.ineterfaces.DBImplementing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DBImplementation implements DBImplementing {

    public DBImplementation(){

    }

    @Override
    public void createDb(String query) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/java/org/example/database/Database.db");
             Statement stmt = conn.createStatement()
        ) {
            stmt.execute(query);

        }catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public String[] listDbs() throws SQLException {

        String query = "SELECT bots from botList";
        Object [] objectArray = readDb(query, "bots");
        String [] allMessages = new String[objectArray.length];
        for (int i = 0; i< objectArray.length; i++) {
            allMessages[i] = String.valueOf(objectArray[i]);
        }
    return allMessages;
    }

    @Override
    public void writeDb(String query) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/java/org/example/database/Database.db");
             Statement stmt = conn.createStatement();
             ) {
            stmt.executeUpdate(query);
        }
    }

    @Override
    public Object[] readDb(String query, String columnName) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/java/org/example/database/Database.db");
             Statement stmt = conn.createStatement()

        ) {
            List<Object> allMessages = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery(query);
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    Object value = resultSet.getObject(i);
                    allMessages.add(value);
                }

            }
            return allMessages.toArray();

        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}


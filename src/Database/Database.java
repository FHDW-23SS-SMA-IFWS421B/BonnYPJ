package Database;
import java.sql.*;
import java.util.Arrays;
import java.util.Collections;

public class Database {


    public static void createDatabase (){

        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/database/Database.db");
            Statement stmt = conn.createStatement();
        ) {
            String createLogsTableQuery = "CREATE TABLE IF NOT EXISTS logs (username VARCHAR(50) PRIMARY KEY, "
                    + "time INT, message VARCHAR(1000), bot VARCHAR(50))";
            String createUserTableQuery = "CREATE TABLE IF NOT EXISTS user_credentials (id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "username VARCHAR(50), password VARCHAR(50))";
            String createBotTableQuery = "CREATE TABLE IF NOT EXISTS bots (bots VARCHAR(50) PRIMARY KEY, status VARCHAR(50))";
            stmt.executeUpdate(createLogsTableQuery);
            stmt.executeUpdate(createUserTableQuery);
            stmt.executeUpdate(createBotTableQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void messageIntoDatabase(String username, Integer time, String message, String bot) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/database/Database.db");
             Statement stmt = conn.createStatement();
        ) {
            String query = "INSERT INTO logs (UserName, message) VALUES ('" + username + "', '" + time + "', '" + message + "', '" + bot + "')";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public String[] getMessages(String username) {
        String[] allMessages = new String[]{};
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/database/Database.db");
             Statement stmt = conn.createStatement();
        ) {
            String query = "select * from logs where username = '" + username + "' order by time desc limit 100";
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                String message = resultSet.getString("message");
                allMessages = Arrays.copyOf(allMessages, allMessages.length + 1);
                allMessages[allMessages.length - 1] = message;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Collections.reverse(Arrays.asList(allMessages));
        return allMessages;
        }
    }


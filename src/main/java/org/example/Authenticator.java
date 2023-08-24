package org.example;

import org.example.database.DBHandler;

import java.sql.SQLException;

public class Authenticator {
    public String authenticate(Session session) throws SQLException {
        /* Asks for username/password and then validates them */
        String username = checkUsername(session);
        if (username != null && checkPassword(session, username)) {
            IOHandler.output("SYSTEM", "\n\n\nWilkommen bei PERSA. Dem PERsonal Service Assistant.\n" +
                    "Für Hilfe bei der Nutzung des Bots schreibe '!Persa info'");
            return username;
        }

        else {
            IOHandler.output("SYSTEM", "Nutzername oder Passwort falsch.\n\n");
            return null;

        }

    }

    public String checkUsername(Session session) throws SQLException {
        String username = IOHandler.getPrefixInput(session.getCurrentUser(),
                "Gebe deinen Nutzernamen ein: ");
        String[] userList = DBHandler.getUserList();
        for (int i = 0; i < userList.length; i++) {
            if (username.equals(userList[i])) {
                return username;
            }
        }
        return null;

    }

    public  boolean checkPassword(Session session, String userName) throws SQLException {
        String password = IOHandler.getPrefixInput(session.getCurrentUser(),
                "Gebe deinen Password ein: ");
        if (password.equals(DBHandler.getUserPassword(userName))) {
            return true;
        }
        else {
            return false;
        }
    }

}

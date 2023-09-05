package org.example;

import org.example.database.DBHandler;

public class Authenticator {
    public String authenticate(Session session) {
        /* Asks for username/password and then validates them */
        String username = checkUsername(session);
        if (username != null && checkPassword(session, username)) {
            IOHandler.output(session.getCurrentUser(), "SYSTEM", """



                    Wilkommen bei PERSA. Dem PERsonal Service Assistant.
                    Für Hilfe bei der Nutzung des Bots schreibe "!Persa info\"""");
            return username;
        }

        else {
            IOHandler.output(session.getCurrentUser(), "SYSTEM", "Nutzername oder Passwort falsch.\n\n");
            return null;

        }

    }

    public String checkUsername(Session session) {
        String username = IOHandler.getPrefixInput(session.getCurrentUser(),
                "Gebe deinen Nutzernamen ein: ");
        String[] userList = DBHandler.getUserList();
        for (String s : userList) {
            if (username.equals(s)) {
                return username;
            }
        }
        return null;

    }

    public  boolean checkPassword(Session session, String userName) {
        String password = IOHandler.getPrefixInput(session.getCurrentUser(),
                "Gebe deinen Password ein: ");
        return password.equals(DBHandler.getUserPassword(userName));
    }

}

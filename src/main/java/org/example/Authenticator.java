package org.example;

import org.example.database.DBHandler;
import org.example.bot.bots.Persa;

public class Authenticator {
    public static String authenticate(Session session) {
        /* Asks for username/password and then validates them */
        String username = checkUsername(session);
        if (username != null && checkPassword(session, username)) {
            IOHandler.output(session.getCurrentUser(), "Persa", Persa.getLogs(session.getCurrentUser()));
            IOHandler.output(session.getCurrentUser(), "SYSTEM", """
                    Willkommen bei PERSA. Dem PERsonal Service Assistant.
                    Für Hilfe bei der Nutzung des Bots schreibe "!Persa info\"""");
            return username;
        }

        else {
            IOHandler.output(session.getCurrentUser(), "SYSTEM", "Nutzername oder Passwort falsch.\n\n");
            return null;

        }

    }

    public static String checkUsername(Session session) {
        String username = IOHandler.getPrefixInput(session.getCurrentUser(),
                "Gebe deinen Nutzernamen ein: ",
                "SYSTEM");
        String[] userList = DBHandler.getUserList();
        for (String s : userList) {
            if (username.equals(s)) {
                return username;
            }
        }
        return null;

    }

    public static boolean checkPassword(Session session, String userName) {
        String password = IOHandler.getPrefixInput(session.getCurrentUser(),
                "Gebe deinen Password ein: ",
                "SYSTEM");
        return password.equals(DBHandler.getUserPassword(userName));
    }

}

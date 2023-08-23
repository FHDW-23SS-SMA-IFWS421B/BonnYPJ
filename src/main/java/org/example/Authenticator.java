package org.example;

import org.example.database.DBHandler;

import java.sql.SQLException;

public class Authenticator {
    Session currentSession;

    Authenticator(Session session) throws SQLException {
        /* Asks for username/password and then validates them */
        this.currentSession = session;
        String username = checkUsername();
        if (username != "" && checkPassword(username)) {
            session.setCurrentUser(username);
        }
        else {
            IOHandler.output("SYSTEM", "Fehler bei der Anmeldung\n");
        }

    }

    public String checkUsername() throws SQLException {
        String username = IOHandler.getPrefixInput(currentSession.getCurrentUser(),
                "Gebe deinen Nutzernamen ein: ");
        String[] userList = DBHandler.getUserList();
        for (int i = 0; i < userList.length; i++) {
            if (username.equals(userList[i])) {
                return username;
            }
        }
        return "";

    }

    public  boolean checkPassword(String userName) throws SQLException {
        String password = IOHandler.getPrefixInput(currentSession.getCurrentUser(),
                "Gebe deinen Password ein: ");
        if (password.equals(DBHandler.getUserPassword(userName))) {
            return true;
        }
        else {
            return false;
        }
    }

}

package org.example;

import org.example.bots.Botlist;

public class Authenticator {
    Session currentSession;

    Authenticator(Session session) {
        /* Asks for username/password and then validates them */
        this.currentSession = session;
        String username = checkUsername();
        if (username != "" && checkPassword(username)) {
            session.setCurrentUser(username);
        }
        else {
            IOHandler.output(Botlist.SYSTEM, "Fehler bei der Anmeldung");
        }

    }


    public String checkUsername() {
        String username = IOHandler.getPrefixInput(currentSession.getCurrentUser(),
                "Gebe deinen Nutzernamen ein: ");
        if (true) {                                                                                                     // TODO Check if username is in Database
            return username;
        }
        else {
            return "";
        }
    }

    public  boolean checkPassword(String username) {
        String password = IOHandler.getPrefixInput(currentSession.getCurrentUser(),
                "Gebe deinen Password ein: ");
        if (true) {                                                                                                     // TODO check if password is correct
            return true;
        }
        else {
            return false;
        }
    }

}

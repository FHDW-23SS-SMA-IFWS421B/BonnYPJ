package org.example;

import org.example.Interfaces.SessionInterface;

public class Session implements SessionInterface {
    private String currentUser = "None";

    public Session() {
        /* user authentication and welcome message  */
        Authenticator authenticator = new Authenticator(this);
    }

    @Override
    public void persaCommunication() {

    }

    @Override
    public void closeSession() {

    }

    @Override
    public String getCurrentUser() {
        return null;
    }

    @Override
    public void setCurrentUser(String username) {

    }
}

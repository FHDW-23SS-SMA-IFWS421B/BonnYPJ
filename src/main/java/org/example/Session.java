package org.example;

import org.example.Interfaces.SessionInterface;

public class Session implements SessionInterface {
    private String currentUser = "None";

    public Session() {
        /* user authentication and welcome message  */
        Authenticator authenticator = new Authenticator(this);
    }

    public void startSession() {

    }

    @Override
    public void startSession(Session session) {
    }

    @Override
    public void persaCommunication() {
    }

    @Override
    public void closeSession() {
    }

    @Override
    public String getCurrentUser() {
        return currentUser;
    }

    @Override
    public void setCurrentUser(String username) {
        this.currentUser = username;

    }
}

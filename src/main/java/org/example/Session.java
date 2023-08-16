package org.example;

import org.example.ineterfaces.SessionInterface;

public class Session implements SessionInterface {
    private String currentUser = "None";

    public Session() {
        /* user authentication and welcome message  */
        Authenticator authenticator = new Authenticator(this);
    }

    @Override
    public void communication() {

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

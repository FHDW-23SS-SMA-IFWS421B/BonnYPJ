package org.example;

import org.example.Interfaces.SessionInterface;

public class Session implements SessionInterface {
    private String currentUser = "";

    public Session() {
        /* user authentication and welcome message  */
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
}

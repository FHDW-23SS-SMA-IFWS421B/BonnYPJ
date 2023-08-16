package org.example.Interfaces;

import org.example.Session;

public interface SessionInterface {
    public void persaCommunication();
    public void closeSession();
    public String getCurrentUser();
    public void setCurrentUser(String username);

}

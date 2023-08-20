package org.example.interfaces;

public interface SessionInterface {
    public void communication();
    public void closeSession();
    public String getCurrentUser();
    public void setCurrentUser(String username);

}

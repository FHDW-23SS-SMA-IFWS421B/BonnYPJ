package org.example.ineterfaces;

public interface BaseBotInterface {
    public void activateBot(String botName);
    public String getName();
    public boolean getBotStatus(String botName);
    public void processRequest(String request);

}

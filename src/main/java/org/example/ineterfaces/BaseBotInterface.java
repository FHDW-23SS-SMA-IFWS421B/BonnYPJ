package org.example.ineterfaces;

public interface BaseBotInterface {
    public void activateBot(String botName);
    public void deactiveBot(String botName);
    public boolean getBotStatus(String botName);

    public void processRequest(String request);

}

package org.example.bots;

import org.example.ineterfaces.BaseBotInterface;

public class BaseBot implements BaseBotInterface {

    @Override
    public void activateBot(String botName) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean getBotStatus(String botName) {
        return false;
    }

    @Override
    public void processRequest(String request) {

    }
}

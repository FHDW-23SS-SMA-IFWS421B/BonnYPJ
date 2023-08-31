package org.example.bot;

public interface BasicBotTemplatInterface {
    void processRequest(String request, String username);
    void answer(String username, String botName, String answer);
}
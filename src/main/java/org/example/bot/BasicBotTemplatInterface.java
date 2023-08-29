package org.example.bot;

import org.example.exceptions.InvalidInputException;
import org.example.exceptions.ProcessingError;

import java.util.HashMap;

public interface BasicBotTemplatInterface {
    void processRequest(String request, String username);
    void answer(String username, String botName, String answer) throws InvalidInputException, ProcessingError;
    String commandList();
}

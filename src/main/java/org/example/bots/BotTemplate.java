package org.example.bots;

import org.example.exceptions.InvalidInputException;

public interface BotTemplate {
    void setupCommands();
    String commandList();
    String connection(String input) throws InvalidInputException;
}

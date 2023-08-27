package org.example.bots;

import org.example.exceptions.InvalidInputException;

import java.io.UnsupportedEncodingException;

public interface BotTemplate {
    void setupCommands();
    String commandList();
    String connection(String input) throws InvalidInputException, UnsupportedEncodingException;
    String jsonFormat(String data) throws InvalidInputException;
}

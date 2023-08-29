package org.example.bot;

import org.example.exceptions.InvalidInputException;

import java.io.UnsupportedEncodingException;

public interface tempLate {
    void setupCommands();
    String commandList();
    String connection(String input) throws InvalidInputException, UnsupportedEncodingException;
    String jsonFormat(String data) throws InvalidInputException;
}

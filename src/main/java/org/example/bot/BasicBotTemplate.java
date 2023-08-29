package org.example.bot;

import org.example.IOHandler;
import org.example.exceptions.InvalidInputException;
import org.example.exceptions.ProcessingError;

import java.sql.SQLException;

public abstract class BasicBotTemplate implements BasicBotTemplatInterface {
    @Override
    public void answer(String username, String botName, String answer) throws ProcessingError {
        try {
            IOHandler.output(username, botName, answer);
        } catch (SQLException e) {
            throw new ProcessingError();
        }
    }
}

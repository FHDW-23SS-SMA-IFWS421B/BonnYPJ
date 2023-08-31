package org.example.bot;

import org.example.IOHandler;
import org.example.ineterfaces.BasicBotTemplatInterface;

public abstract class BotTemplate implements BasicBotTemplatInterface {
    public String processingError = "Es gab ein Problem bei der Verarbeitung der Anfrage. Versuche es erneut.";

    @Override
    public void answer(String username, String botName, String answer) {
        IOHandler.output(username, botName, answer);
    }
}
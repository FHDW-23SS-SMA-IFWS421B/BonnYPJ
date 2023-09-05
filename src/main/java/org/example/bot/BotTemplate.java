package org.example.bot;

import org.example.IOHandler;
import org.example.ineterfaces.BotTemplatInterface;

public abstract class BotTemplate implements BotTemplatInterface {
    public String processingError = "Es gab ein Problem bei der Verarbeitung der Anfrage. Versuche es erneut.";

    @Override
    public void answer(String currentUser, String botName, String answer) {
        IOHandler.output(currentUser, botName, "\n" + answer);

    }
}
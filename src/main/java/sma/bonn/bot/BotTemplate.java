package sma.bonn.bot;

import sma.bonn.IOHandler;
import sma.bonn.ineterfaces.BotTemplatInterface;

public abstract class BotTemplate implements BotTemplatInterface {
    public String processingError = "Es gab ein Problem bei der Verarbeitung der Anfrage. Versuche es erneut.";

    @Override
    public void answer(String currentUser, String botName, String answer) {
        IOHandler.output(currentUser, botName, "\n" + answer);

    }
}
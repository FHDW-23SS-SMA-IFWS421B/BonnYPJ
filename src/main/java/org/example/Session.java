package org.example;

import org.example.bots.BotUtilities;
import org.example.ineterfaces.SessionInterface;

import java.lang.reflect.InvocationTargetException;

public class Session implements SessionInterface {
    private String currentUser = "None";
    private String[] exit = {"!exit", "!logout", "!abmelden", "!schließen"};

    public Session() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        /* user authentication and welcome message  */
        boolean end = false;
        while (!end) {
            Authenticator authenticator = new Authenticator(this);
            communication();
        }
    }

    @Override
    public void communication() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        boolean end = false;
        while (!end) {
            String input = IOHandler.getPrefixInput(currentUser, "- ");
            for (String element : exit) {
                if (element.equals(input)) {
                    end = true;
                }
            }
            String[] botAnswer = BotUtilities.sendRequestToBot(input);
            if (botAnswer != null) {
                IOHandler.output(botAnswer[0], botAnswer[1]);
            }

        }

    }

    @Override
    public void closeSession() {

    }

    @Override
    public String getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public void setCurrentUser(String username) {
        this.currentUser = username;

    }
}

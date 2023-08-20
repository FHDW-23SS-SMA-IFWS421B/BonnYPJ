package org.example;

import org.example.bots.BotUtilities;
import org.example.interfaces.SessionInterface;
import static org.example.IOHandler.*;

public class Session implements SessionInterface {
    private String currentUser = "None";
    private String[] exit = {"exit", "logout", "abmelden", "schließen"};

    public Session() {
        /* user authentication and welcome message  */
//        Authenticator authenticator = new Authenticator(this);
        communication();
    }

    @Override
    public void communication() {
        boolean end = false;
        while (!end) {
            String input = getPrefixInput(currentUser, "- ");
            String[] botAnswer = BotUtilities.sendRequestToBot(input);
            output(botAnswer[0], botAnswer[1]);
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

package org.example;

import org.example.bot.BotCaller;
import org.example.ineterfaces.SessionInterface;

public class Session implements SessionInterface {
    private String currentUser = "None";
    private String[] exit = {"!exit", "!logout", "!abmelden", "!schließen"};

    public Session() {
        /* user authentication and welcome message  */
        boolean end = false;
        while (!end) {
            boolean end2 = false;
            while (!end2) {
                Authenticator authenticator = new Authenticator();
                String username = authenticator.authenticate(this);
                if (username != null) {
                    this.currentUser = username;
                    end2 = true;
                }
            }
            communication();
        }
    }

    @Override
    public void communication() {
        boolean end = false;
        while (!end) {
            String input = IOHandler.getPrefixInput(currentUser, "\n- ");
            for (String element : exit) {
                if (element.equals(input)) {
                    IOHandler.output(currentUser, "SYSTEM", "\n");
                    end = true;
                }
            }
            BotCaller botCaller = new BotCaller(input, currentUser);
        }
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
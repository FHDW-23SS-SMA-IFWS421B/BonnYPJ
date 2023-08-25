package org.example;

import org.example.bots.BotUtilities;
import org.example.ineterfaces.SessionInterface;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Session implements SessionInterface {
    private String currentUser = "None";
    private String[] exit = {"!exit", "!logout", "!abmelden", "!schließen"};

    public Session() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
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
    public void communication() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        boolean end = false;
        while (!end) {
            String input = IOHandler.getPrefixInput(currentUser, "\n- ");
            for (String element : exit) {
                if (element.equals(input)) {
                    IOHandler.output(currentUser, "SYSTEM", "\n");
                    return;
                }
            }

            String[] botAnswer = BotUtilities.sendRequestToBot(currentUser, input);
            if (botAnswer != null) {
                IOHandler.output(currentUser, botAnswer[0], botAnswer[1]);
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

package org.example;

import org.example.bot.BotCaller;
import org.example.bot.BotUtilities;
import org.example.exceptions.IOException;
import org.example.exceptions.ProcessingError;
import org.example.ineterfaces.SessionInterface;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Session implements SessionInterface {
    private String currentUser = "None";
    private String[] exit = {"!exit", "!logout", "!abmelden", "!schließen"};

    public Session() throws IOException, SQLException {
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
    public void communication() throws IOException {
        boolean end = false;
        while (!end) {
            try {
                String input = IOHandler.getPrefixInput(currentUser, "\n- ");
                for (String element : exit) {
                    if (element.equals(input)) {
                        IOHandler.output(currentUser, "SYSTEM", "\n");
                        end = true;
                    }
                }
                BotCaller botCaller = new BotCaller(input, currentUser);
            } catch (SQLException e) {
                throw new IOException();
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

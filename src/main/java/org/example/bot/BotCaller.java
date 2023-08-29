package org.example.bot;

import org.example.IOHandler;
import org.example.bot.bots.Wiki;
import org.example.exceptions.IOException;

import java.sql.SQLException;
import java.util.HashMap;

public class BotCaller {
    HashMap<String, BasicBotTemplate> botObjects = new HashMap<>();
    final String requestError = "Unbekannter Bot. Überprüfe nochmal auf Rechschreibfehler.\n" +
            "Mit !Persa botliste kannst du die die Namen aller Bots anzeigen lassen.\n";

    public BotCaller(String input, String currentUser) throws IOException {
        setBotObjects();


        String[] botRequest = getNameRequestFromInput(input);
        if (botRequest == null) {
            try {
                IOHandler.output("None", "SYSTEM", requestError);
                return;
            } catch (SQLException e) {
                throw new IOException();
            }
        }
        String botName = botRequest[0];
        String request = botRequest[1];

        botName = botName.toLowerCase();
        if (botName.substring(botName.length() -3).equals("bot")) {
            botName = botName.substring(0, botName.length() -3);
        }
        if (botObjects.get(botName) != null) {
            botObjects.get(botName.toLowerCase()).processRequest(request, currentUser);
        } else {
            try {
                IOHandler.output("None", "SYSTEM", requestError);
            } catch (SQLException e) {
                throw new IOException();
            }
        }
    }

    private void setBotObjects() {
        BasicBotTemplate wikiBot = new Wiki();
        botObjects.put("wiki", wikiBot);
    }

    private static String[] getNameRequestFromInput(String input) throws IOException {
        /* Returns bot name and request. If form is not valid returns null */
        String[] arrayOfInput = input.split(" ");
        String[] nameRequest = new String[2];

        if (arrayOfInput.length == 0) {
            try {
                IOHandler.output("None", "SYSTEM", "Gebe einen Befehl ein." +
                        "Schreibe !Persa info für eine Anleitung zur Nutzung.");
                return null;
            } catch (SQLException e) {
                throw new IOException();
            }
        }

        try {
            String exclamationMark = String.valueOf(arrayOfInput[0].charAt(0));
            if (!exclamationMark.equals("!")) {
                try {
                    IOHandler.output("None", "SYSTEM", "Befehle müssen mit einem Ausrufezeichen beginnen");
                    return null;
                } catch (SQLException e) {
                    throw new IOException();
                }
            }

            nameRequest[0] = arrayOfInput[0].substring(1);
            String request = "";
            for (int i = 1; i < arrayOfInput.length; i++){
                request += arrayOfInput[i] + " ";
            }
            request = request.substring(0, request.length() - 1);
            nameRequest[1] = request;
        } catch (Exception e) {
            return null;
        }

        return nameRequest;
    }
}

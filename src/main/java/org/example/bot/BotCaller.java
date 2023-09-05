package org.example.bot;

import org.example.IOHandler;
import org.example.bot.bots.Persa;
import org.example.bot.bots.TranslateBot;
import org.example.bot.bots.WeatherBot;
import org.example.bot.bots.WikiBot;
import org.example.database.DBHandler;

import java.util.HashMap;

public class BotCaller {
    private HashMap<String, BotTemplate> botObjects = new HashMap<>();
    final String requestError = """
            Unbekannter Bot. Überprüfe nochmal auf Rechschreibfehler.
            Mit !Persa botliste kannst du die die Namen aller Bots anzeigen lassen.
            """;

    public BotCaller(String input, String currentUser) {
        setBotObjects();

        String[] arrayOfInput = input.split(" ");
        String botName = getBotRequest(arrayOfInput);
        if (botName == null) {
            return;
        }

        botName = botName.toLowerCase();
        if (botName.length() >= 3) {
            if (botName.endsWith("bot")) {
                botName = botName.substring(0, botName.length() - 3);
            }
        }

        String request = "!" + botName;
        for (int i = 1; i<arrayOfInput.length; i++) {
            request += " " + arrayOfInput[i];
        }

        if (botObjects.get(botName) != null || botName.equals("persa")) {
            if (DBHandler.botStatus(botName) || botName.equals("persa")) {
                botObjects.get(botName).processRequest(request, currentUser);
            } else {
                IOHandler.output(currentUser, botName, String.format("Der %s-Bot ist zurzeit nicht verfügbar.\n" +
                        "Gebe '!Persa aktivieren %s' ein, um ihn wieder zu aktivieren.", botName, botName));
            }
        } else {
            IOHandler.output("None", "SYSTEM", requestError);
        }
    }

    private void setBotObjects() {
        BotTemplate persaBot = new Persa();
        BotTemplate wikiBot = new WikiBot();
        BotTemplate translateBot = new TranslateBot();
        BotTemplate weatherBot = new WeatherBot();
        botObjects.put("persa", persaBot);
        botObjects.put("wiki", wikiBot);
        botObjects.put("translator", translateBot);
        botObjects.put("weather", weatherBot);
    }

    private static String getBotRequest(String[] arrayOfInput) {
        /* Returns bot name and request. If form is not valid returns null */

        if (arrayOfInput.length == 0) {
            IOHandler.output("None", "SYSTEM", "Gebe einen Befehl ein. " +
                    "Schreibe !Persa info für eine Anleitung zur Nutzung.");
            return null;
        }

        String exclamationMark = String.valueOf(arrayOfInput[0].charAt(0));
        if (!exclamationMark.equals("!")) {
            IOHandler.output("None", "SYSTEM", "Befehle müssen mit einem Ausrufezeichen beginnen");
            return null;
        }
        return arrayOfInput[0].substring(1);
    }
}
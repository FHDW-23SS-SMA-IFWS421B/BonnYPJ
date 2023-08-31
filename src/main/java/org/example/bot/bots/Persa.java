package org.example.bot.bots;

import org.example.bot.BotTemplate;
import org.example.database.DBHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Persa extends BotTemplate {
    public String botName = "Persa";
    private Map<String, String> commands = new HashMap<>();

    @Override
    public void processRequest(String request, String username) {
        setupCommands();
        String result = checkCommand(request);
        if (result == null) {
            answer(username, botName, processingError);
        } else {
            answer(username, botName, result);
        }

    }

    private String checkCommand(String request) {
        String answer = null;
        String[] requestSplit = request.split(" ");
        int requestParts = requestSplit.length;

        if (requestParts == 1) {
            return cmdCommands();
        }
        else if (requestParts == 2) {
            switch (requestSplit[1]) {
                case "info":
                    answer = cmdInfo();
                    break;
                case "credits":
                    answer = cmdCredits();
                    break;
                case "log":
                    answer = cmdLog();
                    break;
            }
        }
        else if (requestParts == 3) {
            String name = formatBotName(requestSplit[2]);
            switch (requestSplit[1]) {

                case "aktivieren":
                    answer = cmdAktivieren(name);
                    break;
                case "deaktivieren":
                    answer = cmdDeaktivieren(name);
                    break;
                case "status":
                    answer = cmdStatus(name);
                    break;
            }
        }
        return answer;
    }

    private void setupCommands() {
        commands.put("!Persa info",                     "Gibt informationen zum PERSA Chatsystem aus");
        commands.put("!Persa credits",                  "Zeigt an wer an dem Projekt mitgearbeitet hat");
        commands.put("!Persa log",                      "Gibt den Log der letzten Anfragen und Antworten aus.");
        commands.put("!Persa aktivieren [botname]",     "aktiviert einen Bot, damit er genutzt werden kann.");
        commands.put("!Persa deaktivieren [botname]",   "deaktiviert einen Bot, wodurch er nicht mehr genutzt werden kann.");
    }

    // Commands
    private String cmdCommands() {
        StringBuilder output = new StringBuilder("\nWillkommen beim Wiki-Bot. Dir stehen folgende Befehle zur Verfügung:\n");
        for (String command : commands.keySet()) {
            output.append(" - ").append(command).append(": ").append(commands.get(command)).append("\n");
        }
        return output.toString();
    }

    private String cmdInfo() {
        return "PERSA ist dein PERsonal Service Assistant.\n" +
                "Er bietet ein Chat mit diversen Bots, die dich im Alltag unterstützen können.";
    }

    private String cmdCredits() {
        return """
                PERSA ist im Zuge der Arbeit für 'Softwaremodeling & Architecture Entstanden.
                Es haben folgende Studenten mitgewirkt:
                - Youssef Abdellaoui
                - Jan Hölthe- Phillip Amendt""";
    }

    private String cmdLog() {
        return "TBA";
    }

    private String cmdAktivieren(String name) {
        HashMap<String, String> botStatusList = DBHandler.botStatusList();

        for (Map.Entry<String, String> entry : botStatusList.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.equals(name)) {
                if (value.equals("False")) {
                    DBHandler.activateBot(name);
                    return name + " wurde erfolgreich aktiviert. Er ist jetzt bereit zur Nutzung.";
                } else if (botStatusList.get(key).equals("True")) {
                    return name + " ist bereits aktiv.";
                }
            }
        }
        return String.format("Es wurde kein Bot mit dem Namen '%s' gefunden", name);
    }

    private String cmdDeaktivieren(String name) {
        HashMap<String, String> botStatusList = DBHandler.botStatusList();

        for (Map.Entry<String, String> entry : botStatusList.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.equals(name)) {
                if (value.equals("False")) {
                    DBHandler.deactivateBot(name);
                    return name + " wurde erfolgreich deaktiviert. Es ist jetzt nicht mehr Nutzbar.";
                } else if (botStatusList.get(key).equals("True")) {
                    return name + " ist bereits inaktiv.";
                }
            }
        }
        return String.format("Es wurde kein Bot mit dem Namen '%s' gefunden", name);
    }

    private String cmdStatus(String name) {
        String[] botList = DBHandler.botList();
        for (String s : botList) {
            System.out.println(s + "-" + name);
            if (Objects.equals(s, name)) {
                return String.valueOf(DBHandler.botStatus(name));
            }
        }
        return String.format("Es wurde kein Bot mit dem Namen '%s' gefunden", name);
    }

    // Command Utilities
    private String formatBotName(String name) {
        name = name.toLowerCase();
        if (name.length() >= 3) {
            if (name.endsWith("bot")) {
                name = name.substring(0, name.length() - 3);
            }
        }
        return name;

    }
}

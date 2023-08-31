package org.example.bot.bots;

import org.example.IOHandler;
import org.example.bot.BasicBotTemplate;
import org.example.database.DBHandler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Persa extends BasicBotTemplate {
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
            return commandList();
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

    private String cmdInfo() {
        return "PERSA ist dein PERsonal Service Assistant.\n" +
                "Er bietet ein Chat mit diversen Bots, die dich im Alltag unterstützen können.";
    }

    private String cmdCredits() {
        return "PERSA ist im Zuge der Arbeit für 'Softwaremodeling & Architecture Entstanden.\n" +
                "Es haben folgende Studenten mitgewirkt:\n" +
                "- Youssef Abdellaoui\n" +
                "- Jan Hölthe" +
                "- Phillip Amendt";
    }

    private String cmdLog() {
        return "TBA";
    }

    private String formatBotName(String name) {
        name = name.toLowerCase();
        if (name.length() >= 3) {
            if (name.substring(name.length() -3).equals("bot")) {
                name = name.substring(0, name.length() - 3);
            }
        }
        return name;

    }

    private String cmdAktivieren(String name) {
        HashMap<String, String> botStatusList = DBHandler.botStatusList();

        for (Map.Entry<String, String> entry : botStatusList.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.equals(name)) {
                if (botStatusList.get(key).equals("False")) {
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
                if (botStatusList.get(key).equals("False")) {
                    DBHandler.deactivateBot(name);
                    return name + " wurde erfolgreich deaktiviert. Es ist jetzt nicht mehr Nutzbar.";
                } else if (botStatusList.get(key).equals("True")) {
                    return name + " ist bereits inaktiv.";
                }
            }
        }
        return String.format("Es wurde kein Bot mit dem Namen '%s' gefunden", name);
    }

    public String cmdStatus(String name) {
        String[] botList = DBHandler.botList();
        for (int i = 0; i < botList.length; i++) {
            System.out.println(botList[i] + "-" + name);
            if (botList[i] == name) {
                return String.valueOf(DBHandler.botStatus(name));
            }
        }
        return String.format("Es wurde kein Bot mit dem Namen '%s' gefunden", name);
    }

    private void setupCommands() {
        commands.put("!Persa info",                     "Gibt informationen zum PERSA Chatsystem aus");
        commands.put("!Persa credits",                  "Zeigt an wer an dem Projekt mitgearbeitet hat");
        commands.put("!Persa log",                      "Gibt den Log der letzten Anfragen und Antworten aus.");
        commands.put("!Persa aktivieren [botname]",     "aktiviert einen Bot, damit er genutzt werden kann.");
        commands.put("!Persa deaktivieren [botname]",   "deaktiviert einen Bot, wodurch er nicht mehr genutzt werden kann.");
    }

    private String commandList() {
        StringBuilder output = new StringBuilder("\nWillkommen beim Wiki-Bot. Dir stehen folgende Befehle zur Verfügung:\n");
        for (String command : commands.keySet()) {
            output.append(" - ").append(command).append(": ").append(commands.get(command)).append("\n");
        }
        return output.toString();
    }
}

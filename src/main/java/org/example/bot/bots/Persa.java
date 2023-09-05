package org.example.bot.bots;

import org.example.bot.BotTemplate;
import org.example.database.DBHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Persa extends BotTemplate {
    public String botName = "persa";
    private Map<String, String> commands = new HashMap<>();

    @Override
    public void processRequest(String request, String currentUser) {
        setupCommands();
        String result = checkCommand(request, currentUser);
        if (result == null) {
            answer(currentUser, botName, processingError);
        } else {
            answer(currentUser, botName, result);
        }

    }

    private String checkCommand(String request, String currentUser) {
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
                    answer = cmdLog(currentUser);
                    break;
                case "botliste":
                    answer = cmdBotlist();
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
        commands.put("!Persa botliste",                 "Gibt eine Liste der Bots und ihr Status aus.");
        commands.put("!Persa status [botname]",         "Gibt den Status eines Bots an.");
        commands.put("!Persa aktivieren [botname]",     "aktiviert einen Bot, damit er genutzt werden kann.");
        commands.put("!Persa deaktivieren [botname]",   "deaktiviert einen Bot, wodurch er nicht mehr genutzt werden kann.");
    }

    // Commands
    private String cmdCommands() {
        StringBuilder output = new StringBuilder("Willkommen beim Wiki-Bot. Dir stehen folgende Befehle zur Verfügung:\n");
        for (String command : commands.keySet()) {
            output.append(" - ").append(command).append(": ").append(commands.get(command)).append("\n");
        }
        return output.toString();
    }

    private String cmdInfo() {
        return "PERSA ist dein PERsonal Service Assistant.\n" +
                "Er bietet ein Chat mit diversen Bots, die dich im Alltag unterstützen können.\n" +
                "Schreibe '!Persa' für eine Liste an Commands.\n" +
                "Schreibe '!Persa botliste' für eine Liste an Bots\n" +
                "Schreibe '![botname] für Informationen zur Nutzung des jeweiligen Bots.\n";
    }

    private String cmdBotlist() {
        HashMap<String, String> botStatusList = DBHandler.botStatusList();
        String answer = "";
        for (Map.Entry<String, String> entry : botStatusList.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String name = key.substring(0, 1).toUpperCase() + key.substring(1);
            String status = "(nicht aktiv)";
            if (value.equals("true")) {
                status = "(aktiv)";
            }
            answer += String.format("%s %s\n", name, status);
        }
        return answer;
    }

    private String cmdCredits() {
        return """
                PERSA ist im Zuge der Arbeit für 'Softwaremodeling & Architecture Entstanden.
                Es haben folgende Studenten mitgewirkt:
                - Youssef Abdellaoui
                - Jan Hölthe
                - Phillip Amendt""";
    }

    private String cmdLog(String username) {
        return getLogs(username);
    }

    private String cmdAktivieren(String name) {
        HashMap<String, String> botStatusList = DBHandler.botStatusList();

        for (Map.Entry<String, String> entry : botStatusList.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.equals(name)) {
                System.out.println(value);
                if (value.equals("false")) {
                    DBHandler.activateBot(name);
                    return name + " wurde erfolgreich aktiviert. Er ist jetzt bereit zur Nutzung.";
                } else if (value.equals("true")) {
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
                if (value.equals("true")) {
                    DBHandler.deactivateBot(name);
                    return name + " wurde erfolgreich deaktiviert. Es ist jetzt nicht mehr Nutzbar.";
                } else if (value.equals("false")) {
                    return name + " ist bereits inaktiv.";
                }
            }
        }
        return String.format("Es wurde kein Bot mit dem Namen '%s' gefunden", name);
    }

    private String cmdStatus(String name) {
        String[] botList = DBHandler.botList();
        for (String s : botList) {
            if (Objects.equals(s, name)) {
                boolean status = DBHandler.botStatus(name);
                if (status) {
                    return String.format("Der %s-Bot ist aktiv.", name.substring(0, 1).toUpperCase() + name.substring(1));
                }
                else {
                    return String.format("Der %s-Bot ist nicht aktiv.", name.substring(0, 1).toUpperCase() + name.substring(1));
                }
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

    public static String getLogs(String username) {
        HashMap<Integer, String[]> logMap =  DBHandler.readLogs(username);
        String logOutput = "\n--- Deine letzten Nachrichten ---\n";

        for (Map.Entry<Integer, String[]> entry : logMap.entrySet()) {
            Integer key = entry.getKey();
            String[] value = entry.getValue();
            if (value.length > 0) {
                if (!value[3].equals("SYSTEM") && value[0].equals(username)) {
                    if (value[2].length() > 0){
                        String messageStart = value[2].substring(0, 4).replaceAll("\n", "");
                        if (messageStart.equals("---"))
                            value[2] = "AUSGABE DER LOGS";
                        else {
                            String a = value[2].substring(0, 3).replaceAll("\n", "");
                        }
                    }
                    if(value[3].equals("")) {
                        logOutput+= "- User Input -\n";
                        logOutput += "Username: " + value[0] + "\n";
                        logOutput += "Time: " + value[1] + "\n";
                        logOutput += "Message: " + value[2].replaceAll("\n", " ") + "\n";
                        logOutput += "--------------------\n";
                    } else {
                        logOutput+= "- Bot Output -\n";
                        logOutput += "Time: " + value[1] + "\n";
                        logOutput += "Message: " + value[2].replaceAll("\n", "\n| ") + "\n";
                        logOutput += "Bot: " + value[3] + "\n";
                        logOutput += "--------------------\n";

                    }
                }
            }
        }
        return logOutput + "\n" +
                "--- ENDE DER LOGS ---\n\n";
    }
}

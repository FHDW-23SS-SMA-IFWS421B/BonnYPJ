package org.example.bots;

import org.example.apiConnection.APIConnect;
import org.example.exceptions.InvalidInputException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TranslateBot implements BotTemplate{

    private static final String API_KEY = "";
    private static final String CURRENT_WEATHER_API_URL = "";

    private APIConnect apiConnection = new APIConnect();
    private Map<String, String> commands = new HashMap<>();
    private String result = null;


    public TranslateBot(String input) throws InvalidInputException, UnsupportedEncodingException {
        setupCommands();
        String result = connection(input);
        System.out.println(result);
    }

    @Override
    public void setupCommands() {
        commands.put("!translator", "+ [EN], [DE], etc. für die zu übersetzende Sprache.");
    }

    @Override
    public String commandList() {
        StringBuilder output = new StringBuilder("Willkommen beim Übersetzerbot. Dir stehen folgende Befehle zur Verfügung:\n");
        for (String command : commands.keySet()) {
            output.append(" - ").append(command).append(": ").append(commands.get(command)).append("\n");
        }
        return output.toString();
    }

    @Override
    public String connection(String input) throws InvalidInputException, UnsupportedEncodingException {
        JSONObject translateData;

        String[] command = input.split(" ");

        Map<String, String> languageMap = new HashMap<>();
        languageMap.put("DE", "Deutsch");
        languageMap.put("EN", "Englisch");
        languageMap.put("IT", "Italienisch");
        languageMap.put("ES", "Spanisch");

        //Scanner scanner = new Scanner(System.in);
        //String userInput = scanner.nextLine().toUpperCase(); // Convert input to uppercase

        String selectedLanguage = languageMap.get(command[1]);

        if (selectedLanguage != null) {
            String[] language = {selectedLanguage};
            return "Selected language: " + language[0];
        } else {
            return "Invalid language code.";
        }
    }

    @Override
    public String jsonFormat(String data) throws InvalidInputException {
        return null;
    }

    private String getLanguage() {
        return null;
    }
}

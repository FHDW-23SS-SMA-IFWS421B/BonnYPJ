package org.example.bot.bots;

import org.example.apiConnection.APIConnect;
import org.example.bot.tempLate;
import org.example.exceptions.InvalidInputException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TranslateBot implements tempLate {

    private static final String API_KEY = "3dba9f7c-b663-2d6d-9ded-f5cb55afc8c2:fx";
    private static final String BASE_URL = "https://api-free.deepl.com/v2/translate";

    private APIConnect apiConnection = new APIConnect();
    private Map<String, String> commands = new HashMap<>();
    Map<String, String> languageMap = new HashMap<>();
    private String result = null;


    public TranslateBot(String input) throws InvalidInputException, UnsupportedEncodingException {
        setupCommands();
        String result = connection(input);
        System.out.println(result);
    }

    @Override
    public void setupCommands() {
        commands.put("!translator", "+ [DE], [EN], [ES], [FR], [IT] für die zu übersetzende Sprache.");
    }

    @Override
    public String commandList() {
        StringBuilder output = new StringBuilder("Willkommen beim Übersetzerbot. Dir steht folgender Befehl zur Verfügung\n");
        for (String command : commands.keySet()) {
            output.append(" - ").append(command).append(": ").append(commands.get(command)).append("\n");
        }
        return output.toString();
    }

    @Override
    public String connection(String input) throws InvalidInputException, UnsupportedEncodingException {
        JSONObject translateData;

        if (input.equals("!translator")) {
            return commandList();
        }

        String[] command = input.split(" ");


        languageMap.put("DE", "Deutsch");
        languageMap.put("EN", "Englisch");
        languageMap.put("ES", "Spanisch");
        languageMap.put("FR", "Französisch");
        languageMap.put("IT", "Italienisch");


        String selectedLanguage = languageMap.get(command[1].toUpperCase());

        if (selectedLanguage != null) {
            String[] language = {selectedLanguage};
            System.out.println("Selected language: " + language[0] + "\n Was möchtest du übersetzen lassen?");

            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();

            String apiUrl = buildApiUrl(command[1], text);

            JSONObject translatorData = apiConnection.connectToApi(apiUrl, null);
            String output = jsonFormat(translatorData.toString());
            return output;
        } else {
            return "Invalid language code.";
        }
    }

    @Override
    public String jsonFormat(String data) throws InvalidInputException {
        return null;
    }

    private String buildApiUrl(String language, String text) throws UnsupportedEncodingException {
        String encodedLanguage = URLEncoder.encode(language, "UTF-8");
        String encodedText = URLEncoder.encode(text, "UTF-8");
        return String.format("%s?auth_key=%s&text=%s&target_lang=%s", BASE_URL, API_KEY, encodedText, encodedLanguage);
    }


    private String getLanguage() {
        return null;
    }
}

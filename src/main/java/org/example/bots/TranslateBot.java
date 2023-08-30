package org.example.bots;

import org.example.apiConnection.APIConnect;
import org.example.exceptions.InvalidInputException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TranslateBot implements BotTemplate {

    private static final String API_KEY = "3dba9f7c-b663-2d6d-9ded-f5cb55afc8c2:fx";
    private static final String BASE_URL = "https://api-free.deepl.com/v2/translate";

    private APIConnect apiConnection = new APIConnect();
    private Map<String, String> commands = new HashMap<>();
    Map<String, String> languageMap = new HashMap<>();
    private String result = null;
    private String botName = getName();


    public TranslateBot(String input) throws InvalidInputException, UnsupportedEncodingException {
        setupCommands();
        String result = connection(input);
        System.out.println(result);
    }

    @Override
    public String getName() {
        return "Translator-Bot";
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
        JSONObject translatorData;

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

            translatorData = apiConnection.connectToApi(apiUrl, null);
            String output = jsonFormat(translatorData.toString());
            return output;
        } else {
            return "Invalid language code.";
        }
    }

    @Override
    public String jsonFormat(String data) throws InvalidInputException {
        StringBuilder result = new StringBuilder();
        result.append(botName + ":\n");
        try {
            JSONArray translationsArray = new JSONObject(data).getJSONArray("translations");

            for (int i = 0; i < translationsArray.length(); i++) {
                JSONObject translationObject = translationsArray.getJSONObject(i);
                String text = translationObject.getString("text");

                result.append("Übersetzung: ").append(text);
            }
        } catch (JSONException e) {
            throw new InvalidInputException("Angabe nicht gefunden.");
        }
        if (result.isEmpty()) {
            return "Unbekannte Suchanfrage.";
        } else {
            return result.toString();
        }
    }

    private String buildApiUrl(String language, String text) throws UnsupportedEncodingException {
        String encodedLanguage = URLEncoder.encode(language, "UTF-8");
        String encodedText = URLEncoder.encode(text, "UTF-8");
        return String.format("%s?auth_key=%s&text=%s&target_lang=%s", BASE_URL, API_KEY, encodedText, encodedLanguage);
    }


}

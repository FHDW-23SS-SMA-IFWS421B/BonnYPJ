package org.example.bots;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.example.exceptions.InvalidInputException;
import org.example.apiConnection.APIConnect;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class WikiBot implements BotTemplate {

    private static final String BASE_URL = "https://de.wikipedia.org/w/rest.php/v1/search/page?q=";

    private APIConnect apiConnection = new APIConnect();
    private Map<String, String> commands = new HashMap<>();
    private String result = null;
    private String botName = getName();

    public WikiBot(String input) throws InvalidInputException, UnsupportedEncodingException {
        setupCommands();
        String result = connection(input);
        System.out.println(result);
    }

    public String getName() {
        return "Wiki-Bot";
    }


    @Override
    public void setupCommands() {
        commands.put("!wiki", "Mit dem zu suchenden Begriff, um Infos zu erhalten");
    }

    @Override
    public String commandList() {
        StringBuilder output = new StringBuilder("Willkommen beim Wiki-Bot. Dir stehen folgende Befehle zur Verfügung:\n");
        for (String command : commands.keySet()) {
            output.append(" - ").append(command).append(": ").append(commands.get(command)).append("\n");
        }
        return output.toString();
    }

    @Override
    public String connection(String input) throws InvalidInputException, UnsupportedEncodingException {
        String apiCommand = "";
        StringBuilder apiCommandBuilder = new StringBuilder();

        if (input.equals("!wiki")) {
            return commandList();
        }

        String[] command = input.split(" ");

        for (int i = 1; i < command.length; i++) {
            apiCommandBuilder.append(command[i]);
        }

        String apiUrl = buildApiUrl(input);
        JSONObject wikiData = apiConnection.connectToApi(apiUrl, null);
        String output = jsonFormat(wikiData.toString());

        return output;
    }


    public String jsonFormat(String data) throws InvalidInputException {
        StringBuilder result = new StringBuilder();
        result.append(botName + ":\n");
        try {
            JSONArray pagesArray = new JSONObject(data).getJSONArray("pages");

            for (int i = 0; i < pagesArray.length(); i++) {
                JSONObject pageObject = pagesArray.getJSONObject(i);
                String title = pageObject.getString("title");
                String description = pageObject.getString("description");

                result.append("- ").append(title).append(": ").append(description).append("\n");
            }
        } catch (JSONException e) {
            throw new InvalidInputException("Angabe nicht gefunden.");
        }
        if (result.isEmpty()) {
            return botName + ": Unbekannte Suchanfrage.\n";
        } else {
            return result.toString();
        }


    }

    private String buildApiUrl(String input) throws UnsupportedEncodingException {
        String encodedInput = URLEncoder.encode(input, "UTF-8");
        return String.format("%s%s&limit=3", BASE_URL, encodedInput);
    }


}

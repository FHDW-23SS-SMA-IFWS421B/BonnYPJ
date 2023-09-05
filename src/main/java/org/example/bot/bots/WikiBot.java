package org.example.bot.bots;

import org.example.bot.BotTemplate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.example.apiConnection.APIConnect;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class WikiBot extends BotTemplate {
    public String botName = "wiki";
    private static final String BASE_URL = "https://de.wikipedia.org/w/rest.php/v1/search/page?q=";
    private APIConnect apiConnection = new APIConnect();
    private Map<String, String> commands = new HashMap<>();

    @Override
    public void processRequest(String request, String username) {
        setupCommands();
        String result = connection(request);
        if (result == null) {
            answer(username, botName, processingError);
        } else {
            answer(username, botName, result);
        }

    }

    private void setupCommands() {
        commands.put("!wiki", "Mit dem zu suchenden Begriff, um Infos zu erhalten");
    }

    private String commandList() {
        StringBuilder output = new StringBuilder("\nWillkommen beim Wiki-Bot. Dir stehen folgende Befehle zur Verfügung:\n");
        for (String command : commands.keySet()) {
            output.append(" - ").append(command).append(": ").append(commands.get(command)).append("\n");
        }
        return output.toString();
    }

    private String connection(String input) {
        String apiCommand = "";
        StringBuilder apiCommandBuilder = new StringBuilder();

        if (input.equals("!wiki")) {
            return commandList();
        }

        String[] command = input.split(" ");

        for (int i = 1; i < command.length; i++) {
            apiCommandBuilder.append(command[i]);
        }

        String apiUrl = null;
        apiUrl = buildApiUrl(input);
        if (apiUrl == null) {
            return null;
        }
        JSONObject wikiData = apiConnection.connectToApi(apiUrl, null);
        String output = null;
        output = jsonFormat(wikiData.toString());

        if(output == null) {
            return null;
        }

        if(output.equals("Wiki-Bot:\n")){
            return "Unbekannte Suchanfrage";
        }

        return output;
    }

    private String jsonFormat(String data) {
        StringBuilder result = new StringBuilder();
        result.append(botName + ":\n");
        JSONArray pagesArray = null;
        try {
            pagesArray = new JSONObject(data).getJSONArray("pages");
            for (int i = 0; i < pagesArray.length(); i++) {
                JSONObject pageObject = pagesArray.getJSONObject(i);
                String title = pageObject.getString("title");
                String description = pageObject.getString("description");

                result.append("- ").append(title).append(": ").append(description).append("\n");
            }
        } catch (JSONException e) {
            return null;
        }


        if (result.isEmpty()) {
            return botName + ": Unbekannte Suchanfrage.\n";
        } else {
            return result.toString();
        }


    }

    private String buildApiUrl(String input) {
        String encodedInput = null;
        try {
            encodedInput = URLEncoder.encode(input, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        return String.format("%s%s&limit=3", BASE_URL, encodedInput);
    }
}

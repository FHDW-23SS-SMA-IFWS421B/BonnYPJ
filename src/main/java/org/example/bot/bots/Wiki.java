package org.example.bot.bots;

import org.example.apiConnection.APIConnect;
import org.example.bot.ApiBotTemplate;

import java.util.HashMap;
import java.util.Map;

public class Wiki extends ApiBotTemplate {
    private static final String API_KEY = "e6409848f08fd04167779a4c19729199";
    private static final String CURRENT_WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String FORECAST_API_URL = "http://api.openweathermap.org/data/2.5/forecast/daily";
    private APIConnect apiConnection = new APIConnect();
    private Map<String, String> commands = new HashMap<>();

    @Override
    public void processRequest(String request, String username) {
        setupCommands();
        if (request.equals("info")) {
            System.out.println(commands());
        }
    }

    private void setupCommands() {
        commands.put("info", "Mit dem zu suchenden Begriff, um Infos zu erhalten");
    }

    @Override
    public String commands() {
        StringBuilder output = new StringBuilder("\nWillkommen beim Wiki-Bot. Dir stehen folgende Befehle zur Verfügung:\n");
        for (String command : commands.keySet()) {
            output.append(" - ").append(command).append(": ").append(commands.get(command));
        }
        return output.toString();
    }
}

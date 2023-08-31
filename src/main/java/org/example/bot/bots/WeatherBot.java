package org.example.bot.bots;

import org.example.bot.ApiBotTemplate;
import org.json.JSONException;
import org.json.JSONObject;
import org.example.apiConnection.APIConnect;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class WeatherBot extends ApiBotTemplate {
    public String botName = "Weather-Bot";
    private static final String API_KEY = "e6409848f08fd04167779a4c19729199";
    private static final String CURRENT_WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String FORECAST_API_URL = "http://api.openweathermap.org/data/2.5/forecast/daily";
    private APIConnect apiConnection = new APIConnect();
    private Map<String, String> commands = new HashMap<>();
    private String result = null;


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
        commands.put("!weather", "Aktuelles Wetter");
        commands.put("!weather Prognose", "Wetterprognose");
    }

    private String commandList() {
        StringBuilder output = new StringBuilder("Willkommen beim Wetterbot. Dir stehen folgende Befehle zur Verfügung:\n");
        for (String command : commands.keySet()) {
            output.append(" - ").append(command).append(": ").append(commands.get(command)).append("\n");
        }
        return output.toString();
    }

    private String connection(String input) {

        JSONObject weatherData;


        if (input.equals("!weather")) {
            return commandList();
        }

        String[] command = input.split(" ");

        if (!commands.containsKey(command[0])) {
            return null;
        }

        boolean forecast = command.length == 3 && command[2].equals("Prognose");
        String location = command[1];

        String apiUrl;
        try {
            apiUrl = buildApiUrl(location, forecast);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        if (!forecast) {
            weatherData = apiConnection.connectToApi(apiUrl, null);
        } else {
            return "Forecast nicht vorhanden.";
        }
        result = jsonFormat(weatherData.toString());
        return result;
    }

    private String jsonFormat(String data) {
        StringBuilder result = new StringBuilder();
        result.append(botName + ":\n");
        try {
            JSONObject jsonObject = new JSONObject(data);
            String cityName = jsonObject.getString("name");
            JSONObject mainObject = jsonObject.getJSONObject("main");
            double temperature = mainObject.getDouble("temp");
            JSONObject windObject = jsonObject.getJSONObject("wind");
            double windSpeed = windObject.getDouble("speed");

            result.append("Das Wetter in ").append(cityName).append(":\n");
            result.append("- Temperatur: ").append(temperature).append(" °C\n");
            result.append("- Windgeschwindigkeit: ").append(windSpeed).append(" m/s\n");

        } catch (JSONException e) {
            return null;
        }

        if (result.length() == 0) {
            return "Unbekannte Suchanfrage.";
        } else {
            return result.toString();
        }

    }

    private String buildApiUrl(String location, boolean forecast) throws UnsupportedEncodingException {
        String encodedLocation = URLEncoder.encode(location, "UTF-8");
        String baseUrl = forecast ? FORECAST_API_URL : CURRENT_WEATHER_API_URL;
        return String.format("%s?q=%s&appid=%s&units=metric", baseUrl, encodedLocation, API_KEY);
    }


}

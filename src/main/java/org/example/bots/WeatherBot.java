package org.example.bots;

import org.json.JSONException;
import org.json.JSONObject;

import org.example.exceptions.InvalidInputException;
import org.example.apiConnection.APIConnect;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class WeatherBot implements BotTemplate {

    private static final String API_KEY = "e6409848f08fd04167779a4c19729199";
    private static final String CURRENT_WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String FORECAST_API_URL = "http://api.openweathermap.org/data/2.5/forecast/daily";

    private APIConnect apiConnection = new APIConnect();
    private Map<String, String> commands = new HashMap<>();
    private String result = null;
    private String botName = getName();

    public WeatherBot(String input) throws InvalidInputException {
        setupCommands();
        String result = connection(input);
        System.out.println(result);
    }

    @Override
    public String getName() {
        return "Weather-Bot";
    }

    public void setupCommands() {
        commands.put("!weather", "Aktuelles Wetter");
        commands.put("!weather Prognose", "Wetterprognose");
    }

    public String commandList() {
        StringBuilder output = new StringBuilder("Willkommen beim Wetterbot. Dir stehen folgende Befehle zur Verfügung:\n");
        for (String command : commands.keySet()) {
            output.append(" - ").append(command).append(": ").append(commands.get(command)).append("\n");
        }
        return output.toString();
    }

    public String connection(String input) throws InvalidInputException {

        JSONObject weatherData;


        if (input.equals("!weather")) {
            return commandList();
        }

        String[] command = input.split(" ");

        if (!commands.containsKey(command[0])) {
            throw new InvalidInputException("Unbekannter Befehl.");
        }

        boolean forecast = command.length == 3 && command[2].equals("Prognose");
        String location = command[1];

        String apiUrl;
        try {
            apiUrl = buildApiUrl(location, forecast);
        } catch (UnsupportedEncodingException e) {
            throw new InvalidInputException("Fehler beim Aufbau der API-URL.");
        }
        if (!forecast) {
            weatherData = apiConnection.connectToApi(apiUrl, null);
        } else {
            return "Forecast nicht vorhanden.";
        }
        result = jsonFormat(weatherData.toString());
        return result;
    }

    @Override
    public String jsonFormat(String data) throws InvalidInputException {
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
            throw new InvalidInputException("Angabe nicht gefunden.");
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

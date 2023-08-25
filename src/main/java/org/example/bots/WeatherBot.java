package org.example.bots;

import org.json.JSONObject;

import org.example.exceptions.InvalidInputException;
import org.example.apiConnection.APIConnect;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class WeatherBot extends BotTemplate {

    private static final String API_KEY = "e6409848f08fd04167779a4c19729199";
    private static final String CURRENT_WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String FORECAST_API_URL = "http://api.openweathermap.org/data/2.5/forecast/daily";

    private APIConnect apiConnection = new APIConnect();
    private Map<String, String> commands = new HashMap<>();
    private String result = null;

    public WeatherBot(String input) throws InvalidInputException {
        setupCommands();
        String result = connection(input);
        System.out.println(result);
    }

    private void setupCommands() {
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

        if (input.equals("!weather")){
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

        JSONObject weatherData = apiConnection.connectToApi(apiUrl, null);

        if (weatherData != null && weatherData.has("name")) {
            if (!forecast) {
                if (!forecast) {
                    try {
                        String cityName = weatherData.optString("name", "Unknown City");
                        JSONObject main = weatherData.getJSONObject("main");
                        double temperature = main.optDouble("temp", Double.NaN);
                        double windSpeed = weatherData.getJSONObject("wind").optDouble("speed", Double.NaN);

                        if (!Double.isNaN(temperature) && !Double.isNaN(windSpeed)) {
                            result = "Das Wetter in " + cityName + ": \n Temperatur: " + temperature + "°C \n Windgeschwindigkeit: "
                                    + windSpeed + " m/s";
                        } else {
                            throw new InvalidInputException("Ungültige Daten vom API erhalten");
                        }
                    } catch (Exception e) {
                        throw new InvalidInputException("Stadtname nicht gefunden, bitte überprüfe die Schreibweise.");
                    }
                } else {
                    return weatherData.toString();
                }
            } else {
                throw new InvalidInputException("Der Wetterdienst konnte nicht erreicht werden.");
            }

        }
        return result;
    }

    private String buildApiUrl(String location, boolean forecast) throws UnsupportedEncodingException {
        String encodedLocation = URLEncoder.encode(location, "UTF-8");
        String baseUrl = forecast ? FORECAST_API_URL : CURRENT_WEATHER_API_URL;
        return String.format("%s?q=%s&appid=%s&units=metric", baseUrl, encodedLocation, API_KEY);
    }
}
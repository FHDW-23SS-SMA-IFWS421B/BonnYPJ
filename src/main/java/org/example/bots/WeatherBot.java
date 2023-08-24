package org.example.bots;

import org.json.JSONObject;

import org.example.exceptions.InvalidInputException;
import org.example.apiConnection.APIConnect;


import java.util.Locale;

public class WeatherBot {

    private String API_KEY = "e6409848f08fd04167779a4c19729199";
    private APIConnect apiConnection = new APIConnect();
    private boolean forecast = false;
    private String location = null;
    private JSONObject weatherData = null;
    private String result = null;

    public WeatherBot(String input) throws InvalidInputException {
        String result = connection(input);
        System.out.println(result);
    }

    public String connection(String input) throws InvalidInputException {
        String[] command = input.split(" ");

        if (command[0].equals("exit")) {
            throw new InvalidInputException("Bot closed.");
        }else{
            location = command[0];
            if (command[1].equals("Vorhersage")){
                forecast = true;
            }
        }



        char lastCoreLetter = location.charAt(location.length() - 1);
        if (lastCoreLetter == '!' || lastCoreLetter == '?' || lastCoreLetter == '.') {
            location = location.substring(0, location.length() - 1);
        }

        String apiUrl = forecast ? "http://api.openweathermap.org/data/2.5/forecast/daily?q=" + location + "&cnt=5&appid=" + API_KEY
                + "&units=metric"
                : "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + API_KEY
                + "&units=metric";


        weatherData = apiConnection.connectToApi(apiUrl, null);

        if (weatherData != null && !weatherData.isNull("name")) {
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
            throw new InvalidInputException("Stadtname nicht gefunden, bitte überprüfe die Schreibweise.");
        }

        return result;
    }


}


package sma.bonn.bot.bots;

import org.json.JSONArray;
import sma.bonn.bot.BotTemplate;
import org.json.JSONException;
import org.json.JSONObject;
import sma.bonn.apiConnection.APIConnect;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WeatherBot extends BotTemplate {
    public String botName = "weather";
    private static final String API_KEY = "e6409848f08fd04167779a4c19729199";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
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

        boolean forecast = command.length == 3 && command[2].toLowerCase().equals("prognose");
        String location = command[1];

        String apiUrl;
        try {
            apiUrl = buildApiUrl(location, forecast);
        } catch (UnsupportedEncodingException e) {
            return null;
        }

        weatherData = apiConnection.connectToApi(apiUrl, null);

        String result = jsonFormat(weatherData.toString(), forecast);
        return result;
    }

    private String jsonFormat(String data, boolean forecast) {
        StringBuilder result = new StringBuilder();
        result.append(botName + ":\n");
        if (forecast) {
            try {
                JSONObject json = new JSONObject(data);

                String cityName = json.getJSONObject("city").getString("name");
                JSONArray weatherList = json.getJSONArray("list");

                System.out.println("Das Wetter in " + cityName + " in den nächsten Tagen:");

                for (int i = 0; i < weatherList.length(); i++) {
                    JSONObject weatherData = weatherList.getJSONObject(i);
                    String dateTime = weatherData.getString("dt_txt");

                    if (dateTime.endsWith("15:00:00")) {
                        SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        SimpleDateFormat sdfOutput = new SimpleDateFormat("dd.MM.yyyy");

                        try {
                            Date date = sdfInput.parse(dateTime);
                            String formattedDate = sdfOutput.format(date);
                            double temperature = weatherData.getJSONObject("main").getDouble("temp");
                            double windSpeed = weatherData.getJSONObject("wind").getDouble("speed");

                            result.append("Tag: " + formattedDate + ", Temperatur: " + temperature + " °C, Windgeschwindigkeit: " + windSpeed + " m/s\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            } catch (
                    JSONException e) {
                return null;
            }
        } else {

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

            } catch (
                    JSONException e) {
                return null;
            }
        }

        if (result.length() == 0) {
            return "Unbekannte Suchanfrage.";
        } else {
            return result.toString();
        }
    }

    private String buildApiUrl(String location, boolean forecast) throws UnsupportedEncodingException {
        String encodedLocation = URLEncoder.encode(location, "UTF-8");
        String currentWeather = "weather";
        String forecastWeather = "forecast";
        String decideForecast = forecast ? forecastWeather : currentWeather;
        return String.format("%s%s?q=%s&appid=%s&units=metric", BASE_URL, decideForecast, encodedLocation, API_KEY);
    }


}


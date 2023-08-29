package org.example.bot.bots;

import org.example.bot.ApiBotTemplate;
import org.example.exceptions.InvalidInputException;
import org.example.exceptions.ProcessingError;

public class Wiki extends ApiBotTemplate {
    private static final String API_KEY = "e6409848f08fd04167779a4c19729199";
    private static final String CURRENT_WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String FORECAST_API_URL = "http://api.openweathermap.org/data/2.5/forecast/daily";

    @Override
    public void processRequest(String request, String username) {
        System.out.println(request);
    }

    @Override
    public String commandList() {
        return null;
    }
}

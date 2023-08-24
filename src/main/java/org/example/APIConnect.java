//package org.example;
//
//import org.example.bots.BaseBot;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class APIConnect {
//    private final String apiKey;
//
//    public BaseBot(String apiKey) {
//        this.apiKey = apiKey;
//    }
//
//    public String sendGetRequest(String url) throws IOException {
//        URL apiUrl = new URL(url);
//        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
//
//        // Setze Anfrage-Methode auf GET
//        connection.setRequestMethod("GET");
//
//        // Füge API-Key zum Anfrage-Header hinzu
//        connection.setRequestProperty("api_key", apiKey);
//
//        int responseCode = connection.getResponseCode();
//
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String inputLine;
//            StringBuilder response = new StringBuilder();
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//
//            in.close();
//            return response.toString();
//        } else {
//            throw new IOException("GET request failed with response code: " + responseCode);
//        }
//    }
//
//    public static void main(String[] args) {
//        String apiKey = "e6409848f08fd04167779a4c19729199";
//        BaseBot bot = new BaseBot(apiKey);
//
//        String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=London&appid=" + apiKey;
//
//        try {
//            String response = bot.sendGetRequest(apiUrl);
//            System.out.println("API Response:\n" + response);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}

package org.example.apiConnection;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIConnect {

    public JSONObject connectToApi(String urlString, String urlParams) {
        JSONObject jsonResponse = new JSONObject();
        String urlParameters = urlParams;

        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setDoOutput(true);

            if (urlParameters != null) {
                con.getOutputStream().write(urlParameters.getBytes("UTF-8"));
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    jsonResponse = new JSONObject(response.toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("API Verbindungsaufbau Fehler: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }
}
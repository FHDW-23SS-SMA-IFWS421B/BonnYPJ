import ApiConnection.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.print("Enter a City name: ");

        String cityName = myObj.nextLine();  // Read user input

        try {
            OpenWeatherMapAPI weatherAPI = new OpenWeatherMapAPI("e6409848f08fd04167779a4c19729199");
            String response = weatherAPI.getCurrentWeather(cityName);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package org.example;

public class Authenticator {
    public static String login() {
        String username = ioHandler.getPrefixInput("Gebe deinen Nutzernamen ein: ");
        if (true) {                                                                         // TODO Check if username is in Database
            return username;
        }
        else {
            return "LOGIN ERROR";
        }
    }
}

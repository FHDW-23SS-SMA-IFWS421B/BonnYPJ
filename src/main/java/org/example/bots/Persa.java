package org.example.bots;

import org.example.IOHandler;

import java.sql.SQLException;

public class Persa {
    private String name = "Persa";
    public String[] commands = {"info", "credits", "help", "commands", "activate", "deactivate", "statuslist", "log"};

    public Persa(String currentUser, String input) throws SQLException {
        answer(processCommand(currentUser), input);
    }

    private String processCommand(String input) {
        String[] inputSplit = input.split(" ");
        String keyword = inputSplit[0];
        switch (keyword) {
            case "info":
                return info();
            case "credits":
                return credits();
            case "help":
                return help();
        }
        return null;
    }

    private void answer(String currentUser, String answer) throws SQLException {
        IOHandler.output(currentUser, "Persa", answer);
    }

    /* COMMANDS */
    private String info() {
        String info = " ";
        return info;
    }

    private String credits() {
        String credits = "";
        return credits;
    }

    private String help() {
        String help = "";
        return help;
    }




}

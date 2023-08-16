package org.example.bots;

import org.example.ineterfaces.BotUtilitiesInterface;

import java.util.HashMap;

public class BotUtilities implements BotUtilitiesInterface {
    @Override
    public String[] getBotNameFromInput(String input) {
        return null;
    }

    @Override
    public boolean checkIfValidRequest(String inout) {
        return false;
    }

    @Override
    public void sendRequestToBot(String[] botAndRequest) {

    }

    @Override
    public String[] getBotList() {
        return new String[0];
    }

    @Override
    public HashMap<String, Boolean> getBotStatusList() {
        return null;
    }
}

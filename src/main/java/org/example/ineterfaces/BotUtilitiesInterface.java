package org.example.ineterfaces;

import java.util.HashMap;

public interface BotUtilitiesInterface {
    public String[] getBotNameFromInput(String input);
    public boolean checkIfValidRequest(String inout);
    public void sendRequestToBot(String botAndRequest[]);

    public String[] getBotList();
    public HashMap<String, Boolean> getBotStatusList();


}

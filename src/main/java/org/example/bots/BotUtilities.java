package org.example.bots;

import org.example.IOHandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class BotUtilities {
    public static String[] sendRequestToBot(String input) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String[] botAnswer = null;
        String[] botRequest  = getNameRequestFromInput(input);
        if (botRequest == null) {
            IOHandler.output("SYSTEM",
                    "Befehl nicht erkannt.\n" +
                            "Benötigte Struktur: '![Botname] [Befehl]\n");
            return null;
        }
        Class<?> botClass = Class.forName("org.example.bots." + botRequest[0]);
        Constructor<?> botConstructor = botClass.getConstructor(String.class);
        Object botObject = botConstructor.newInstance(botRequest[1]);

        botAnswer[0] = botRequest[0];
        botAnswer[1] = "temp_answer";
        return botAnswer;
    }

    public String[] getBotList() {
        return new String[0];
    }

    public HashMap<String, Boolean> getBotStatusList() {
        return null;
    }

    private static String[] getNameRequestFromInput(String input) {              // TODO make private
        /* Returns bot name and requst. If form is not valid returns null */
        String[] arrayOfString = input.split("\\s");
        String[] nameRequest = new String[2];
        if (arrayOfString.length <= 1) {
            return null;
        }

        try {
            String exclamationMark = String.valueOf(arrayOfString[0].charAt(0));
            if (!exclamationMark.equals("!")) {                                     // TODO check if bot exists
                return null;
            }

            nameRequest[0] = arrayOfString[0].substring(1);
            String request = "";
            for (int i = 1; i < arrayOfString.length; i++){
                request += arrayOfString[i] + " ";
            }
            request = request.substring(0, request.length() - 1);
            nameRequest[1] = request;
        } catch (Exception e) {
            return null;
        }

        return nameRequest;
    }
}



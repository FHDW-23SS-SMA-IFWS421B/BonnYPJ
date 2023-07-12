package outputInterface;

import Interface.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Console implements ControlPanel {
    public String input() {
        System.out.print("User: ");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String text = "";
        try {
            text = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public void display(String message) {
        /*  */
        String currentBot = "PERSA";
        String text = "";
        switch (message) {
            case "@wiki":
                currentBot = "Wikibot";
                text = "I am the wiki bot\n";
                break;
            case "@translator":
                currentBot = "Translatebot";
                text = "I am the translator bot\n";
                break;
            case "@weather":
                currentBot = "Weatherbot";
                text = "I am the weather bot\n";
                break;
            default:
                text = "This bot does not exist\n";
                break;
        }
        System.out.print(String.format("%s: ", currentBot) + text);
    }
}

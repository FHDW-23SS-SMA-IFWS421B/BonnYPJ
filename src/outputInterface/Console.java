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
        System.out.print("Bot: ");
        switch (message) {
            case "@wiki":
                System.out.println("I am the wiki bot");
                break;
            case "@translator":
                System.out.println("I am the translator bot");
                break;
            case "@weather":
                System.out.println("I am the weather bot");
                break;
            default:
                System.out.println("This bot does not exist");
                break;
        }
    }
}

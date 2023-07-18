package outputInterface;

import Interface.ControlPanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ioHandler implements ControlPanel {
    @Override
    public String getInput() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String text = "";
        try {
            text = input.readLine();
        } catch (IOException e) {
            System.out.println("INPUT ERROR");
            e.printStackTrace();
        }
        return text;
    }

    @Override
    public String prefixInput(String prefix) {
        output(String.format("%s: ", prefix), false);
        return  getInput();
    }


    @Override
    public void output(String output) {
        System.out.println(output);
    }

    @Override
    public void output(String output, boolean nextLine) {
        if (nextLine) {
            System.out.println(output);
        }
        else {
            System.out.printf(output);
        }
    }
}

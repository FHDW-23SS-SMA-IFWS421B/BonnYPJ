import Interface.ControlPanel;
import outputInterface.*;

public class Main {
    public static void main(String[] args) {
        communicate();
    }

    public static void communicate() {
        ControlPanel console = new Console();
        String input = "";
        while (!input.equals("exit")){
            input = console.input();
            console.display(input);
        }

    }
}
import Interface.ControlPanel;
import Session.Session;
import User.Authenticator;
import outputInterface.*;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Session session = new Session();
        boolean b = true;
        while (true) {
            session.initializeSession();            // login
        }
    }


    public static void communicate() {
        ioHandler console = new ioHandler();
        String input = "";
        while (!input.equals("exit")){
            input = console.getInput();
        }

    }
}
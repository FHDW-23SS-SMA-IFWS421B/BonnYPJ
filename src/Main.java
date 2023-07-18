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
//            session.initializeSession();            // login
            session.persaCommunication();
        }
    }
}
package Session;

import User.Authenticator;
import outputInterface.ioHandler;

public class Session {
    ioHandler io = new ioHandler();
    User.Authenticator auth = new Authenticator();
    private String currentUser = "";
    public void initializeSession() {
        currentUser = auth.login();
    }

    public void persaCommunication() {
        int counter = 0;
        while (counter == 0) {
            String input = io.prefixInput("User");


        }
    }

    public String getCurrentUser() {
        return currentUser;
    }
}

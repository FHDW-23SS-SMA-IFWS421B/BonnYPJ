package Session;

import User.Authenticator;

public class Session {
    private String currentUser = "";
    public void initializeSession() {
        User.Authenticator auth = new Authenticator();
        currentUser = auth.login();
    }

    public String getCurrentUser() {
        return currentUser;
    }
}

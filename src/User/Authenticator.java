package User;

import outputInterface.ioHandler;

import java.util.HashMap;

public class Authenticator implements AuthenticationManager{
    ioHandler io = new ioHandler();
    @Override
    public String login() {
        io.output("Gebe deinen Nutzernamen ein: ", false);
        String username = io.getInput();
        if (username.equals("exit")) {
            io.output("Breche Anmeldung ab...\n");
            login();
            return "LOGIN ERROR";
        }
        if (checkValidUsername(username)) {                                                  // existing username -> password check
            if (checkPassword(username)) {
                return username;
            }
            else {
                io.output("Breche Anmeldung ab...\n");
                login();
                return "LOGIN ERROR";
            }
        }
        else {                                                                               // non-existing username -> retry
            io.output(String.format("Kein Nutzer mit dem Namen '%s' gefunden.\n", username));
            login();
            return username;
        }
    }

    @Override
    public String logout() {
        io.output("Erfolgreich abgemeldet\n");
        return "";
    }

    @Override
    public HashMap getUsers() {
        HashMap<String, String> users = new HashMap<>();
        users.put("jupp", "pw01");
        users.put("jan", "pw02");
        users.put("flip", "pw03");
        return users;
    }

    @Override
    public boolean checkValidUsername(String username) {
        HashMap users = getUsers();
        Object passwd = users.get(username);
        if (passwd != null)
            return true;
        else
            return false;
    }

    @Override
    public boolean checkPassword(String username) {
        HashMap users = getUsers();
        String userpasswd = users.get(username).toString();
        io.output("Gebe dein Passwort ein: ", false);
        String password = io.getInput();
        if (password.equals(userpasswd)) {
            return true;
        } else if (password.equals("exit")) {
            return false;
        } else {
            checkPassword(username);
        }
        return false;
    }
}

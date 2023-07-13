package User;

import java.util.HashMap;

public interface AuthenticationManager {

    public String login();
    public String logout();
    public HashMap getUsers();
    public boolean checkValidUsername(String username);
    public boolean checkPassword(String username);
}

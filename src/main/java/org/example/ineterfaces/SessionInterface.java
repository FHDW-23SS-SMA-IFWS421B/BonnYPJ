package org.example.ineterfaces;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface SessionInterface {
    public void communication();
    public String getCurrentUser();
    public void setCurrentUser(String username);

}

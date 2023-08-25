package org.example.ineterfaces;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface SessionInterface {
    public void communication() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException;
    public void closeSession();
    public String getCurrentUser();
    public void setCurrentUser(String username);

}

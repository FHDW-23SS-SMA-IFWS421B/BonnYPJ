package org.example.ineterfaces;

import java.lang.reflect.InvocationTargetException;

public interface SessionInterface {
    public void communication() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    public void closeSession();
    public String getCurrentUser();
    public void setCurrentUser(String username);

}

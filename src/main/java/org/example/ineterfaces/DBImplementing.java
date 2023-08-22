package org.example.ineterfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DBImplementing {
    /* Direct interaction with the Database. */

    public void createDb(String query);
    public String[] listDbs() throws SQLException;
    public void writeDb(String query) throws SQLException;
    public Object[] readDb(String query, String columnName) throws SQLException;

}

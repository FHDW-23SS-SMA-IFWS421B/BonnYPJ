package org.example.ineterfaces;

public interface DBImplementing {
    /* Direct interaction with the Database. */
    public void executeQuery(String query);
    public void createDb();
    public String[] listDbs();
    public void writeDb();
    public void readDb();

}

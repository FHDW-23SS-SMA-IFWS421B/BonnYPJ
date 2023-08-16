package org.example.database;

import org.example.ineterfaces.DBImplementing;

public class DBImplementation implements DBImplementing {
    @Override
    public void executeQuery(String query) {

    }

    @Override
    public void createDb() {

    }

    @Override
    public String[] listDbs() {
        return new String[0];
    }

    @Override
    public void writeDb() {

    }

    @Override
    public void readDb() {

    }
}

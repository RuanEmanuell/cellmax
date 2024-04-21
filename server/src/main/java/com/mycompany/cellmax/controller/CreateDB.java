package com.mycompany.cellmax.controller;

import java.io.File;
import java.io.IOException;

public class CreateDB {

    public void createDatabase() {
        try {
            File database = new File("database/database.db");
            if (!database.exists()) {
                database.createNewFile();
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}

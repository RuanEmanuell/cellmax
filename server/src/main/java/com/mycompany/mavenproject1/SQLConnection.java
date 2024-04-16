package com.mycompany.mavenproject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    public static void connectDatabase() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:database/database.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Conectado ao SQLite");
        } catch (SQLException excep) {
            System.out.println(excep.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException excep) {
                System.out.println(excep.getMessage());
            }
        }
    }
}

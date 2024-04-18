package com.mycompany.mavenproject1;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    private static Connection conn = null;

    public static void connectDatabase() {
        try {
            String url = "jdbc:sqlite:database/database.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Conectado ao SQLite");
            startDatabase();
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

    public static void startDatabase() {
        String SQL = "CREATE TABLE smartphones(name varchar(100), brand varchar(100)," 
                   + "year int, systemName varchar(100), screenSize float, "
                   + "cpu varchar(100), cameraNumber int,"
                   + "cameraMP int, imageLink varchar(500))";
        databaseOperation(SQL);
        insertDefaultData();
    }
    
    public static void insertDefaultData(){
         String SQL = "INSERT INTO smartphones VALUES ('iPhone X', 'Apple', 2017,"
                 + "'iOS 16', 5.8, 'Apple Bionic 11', 2, 12, 'https://microimport.com.br/wp-content/uploads/iphone-x-seminovo-com-garantia-de-3-meses.webp')";
        databaseOperation(SQL);   
    }
    
    public static void databaseOperation(String SQL){
        try {
            Statement stm = conn.createStatement();
            stm.execute(SQL);
        } catch (SQLException excep) {
            System.out.println(excep.getMessage());
        } 
    }
}

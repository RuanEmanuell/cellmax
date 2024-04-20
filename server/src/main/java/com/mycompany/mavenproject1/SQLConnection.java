package com.mycompany.mavenproject1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        }
    }

    public static void startDatabase() {
        String SQL = "CREATE TABLE smartphones(id integer primary key autoincrement, "
                + "name varchar(100), brand varchar(100),"
                + "year int, systemName varchar(100), screenSize float, "
                + "cpu varchar(100), cameraNumber int,"
                + "cameraMP int, imageLink varchar(500))";
        databaseOperation(SQL);
        insertDefaultData();
    }

    public static void insertDefaultData() {
        String SQL = "INSERT INTO smartphones (name, brand, year, systemName, screenSize, cpu, cameraNumber, cameraMP, imageLink) " +
                "VALUES ('iPhone X', 'Apple', 2017, 'iOS 16', 5.8, 'A11 Bionic', 2, 12, 'https://microimport.com.br/wp-content/uploads/iphone-x-seminovo-com-garantia-de-3-meses.webp')";
        databaseOperation(SQL);
    }

    public static List<Smartphone> getData() {
        String SQL = "SELECT * from smartphones";
        List<Smartphone> smartphoneList = new ArrayList<>();
        ResultSet rs = null;
        try {
            Statement stm = conn.createStatement();
            rs = stm.executeQuery(SQL);
            while(rs.next()){
                Smartphone smartphone = new Smartphone();
                smartphone.setId(rs.getLong("id"));
                smartphone.setName(rs.getString("name"));
                smartphone.setBrand(rs.getString("brand"));
                smartphone.setYear(rs.getInt("year"));
                smartphone.setSystemName(rs.getString("systemName"));
                smartphone.setScreenSize(rs.getDouble("screenSize"));
                smartphone.setCpu(rs.getString("cpu"));
                smartphone.setCameraNumber(rs.getInt("cameraNumber"));
                smartphone.setCameraMP(rs.getInt("cameraMP"));
                smartphone.setImageLink(rs.getString("imageLink"));
                smartphoneList.add(smartphone);
            }
        } catch (SQLException excep) {
            System.out.println(excep.getMessage());
        }
        System.out.println(smartphoneList);
        return smartphoneList;
    }

    private static void databaseOperation(String SQL) {
        try {
            Statement stm = conn.createStatement();
            stm.execute(SQL);
        } catch (SQLException excep) {
            System.out.println(excep.getMessage());
        }
    }
}

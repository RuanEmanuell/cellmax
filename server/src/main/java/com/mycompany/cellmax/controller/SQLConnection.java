package com.mycompany.cellmax.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

public class SQLConnection {

    private Connection conn;

    public void connectDatabase() {
        closeConnection();
        try {
            String url = "jdbc:sqlite:database/database.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Conectado ao SQLite");
            startDatabase();
        } catch (SQLException excep) {
            System.out.println(excep.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }
    
    public void closeConnection() {
    try {
        if (conn != null) {
            conn.close();
            System.out.println("Conexão fechada.");
        }
    } catch (SQLException ex) {
        System.out.println("Erro ao fechar a conexão: " + ex.getMessage());
    }
}

    private void startDatabase() {
        String SQL = "CREATE TABLE IF NOT EXISTS smartphones(id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name VARCHAR(100), brand VARCHAR(100),"
                + "year INT, systemName VARCHAR(100), screenSize FLOAT, "
                + "cpu VARCHAR(100), cameraNumber INT,"
                + "cameraMP INT, imageLink VARCHAR(500))";
        databaseOperation(SQL);
        insertDefaultData();
    }

    private void insertDefaultData() {
        try {
            String checkSQL = "SELECT COUNT(*) FROM smartphones";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(checkSQL);
            rs.next();
            int count = rs.getInt(1);

            if (count == 0) {
                String insertSQL = "INSERT INTO smartphones (name, brand, year, systemName, screenSize, cpu, cameraNumber, cameraMP, imageLink) "
                        + "VALUES ('iPhone X', 'Apple', 2017, 'iOS 16', 5.8, 'A11 Bionic', 2, 12, 'https://microimport.com.br/wp-content/uploads/iphone-x-seminovo-com-garantia-de-3-meses.webp')";
                databaseOperation(insertSQL);
                System.out.println("Dados padrão inseridos com sucesso.");
            } else {
                System.out.println("A tabela smartphones já contém dados. Nenhum dado padrão foi inserido.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir dados padrão: " + ex.getMessage());
        }
    }

    private void databaseOperation(String SQL) {
        try {
            if (getConnection() != null) {
                conn.createStatement().execute(SQL);
            }
        } catch (SQLException excep) {
            System.out.println(excep.getMessage());
        }
    }
}

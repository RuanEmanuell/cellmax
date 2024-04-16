/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ruanemanuell
 */
public class SQLConnection {

    public static void connectDatabase() {
        Connection conn = null;
        try {
            String url = "../../../../../../../database/database.db";
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

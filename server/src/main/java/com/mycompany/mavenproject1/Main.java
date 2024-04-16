/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.mavenproject1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author ruanemanuell
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SQLConnection.connectDatabase();
        SpringApplication.run(Main.class, args);
    }
}
